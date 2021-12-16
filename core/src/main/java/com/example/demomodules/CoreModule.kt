package com.example.demomodules

import com.example.demomodules.core.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
@MustBeDocumented
annotation class OkHttpClientConnectionTimeout

@Retention(AnnotationRetention.BINARY)
@Qualifier
@MustBeDocumented
annotation class OkHttpClientReadTimeout

@Retention(AnnotationRetention.BINARY)
@Qualifier
@MustBeDocumented
annotation class OkHttpClientWriteTimeout

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal interface CoreModule {
  @Binds
  @Singleton
  fun appCoroutineScope(impl: DefaultAppCoroutineScope): AppCoroutineScope

  @Binds
  @Singleton
  fun appCoroutineDispatchers(impl: DefaultAppCoroutineDispatchers): AppCoroutineDispatchers

  @Binds
  @Singleton
  fun remoteConfig(impl: FirebaseRemoteConfigImpl): RemoteConfig

  companion object {
    @Provides
    @Singleton
    fun firebaseRemoteConfig(): FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    @Provides
    @Singleton
    fun moshi(): Moshi {
      return Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    }

    @Provides
    @Singleton
    fun moshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
      MoshiConverterFactory.create(moshi)

    @Provides
    @OkHttpClientReadTimeout
    fun okHttpClientReadTimeout(): Long = 15

    @Provides
    @OkHttpClientConnectionTimeout
    fun okHttpClientConnectionTimeout(): Long = 15

    @Provides
    @OkHttpClientWriteTimeout
    fun okHttpClientWriteTimeout(): Long = 15

    @Provides
    @Singleton
    fun okHttpClient(
      @OkHttpClientConnectionTimeout connectionTimeout: Long,
      @OkHttpClientReadTimeout readTimeout: Long,
      @OkHttpClientWriteTimeout writeTimeout: Long,
    ): OkHttpClient {
      return OkHttpClient.Builder()
        .connectTimeout(connectionTimeout, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS)
        .writeTimeout(writeTimeout, TimeUnit.SECONDS)
        .addInterceptor(
          HttpLoggingInterceptor()
            .apply { level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE }
        )
        .build()
    }
  }
}
