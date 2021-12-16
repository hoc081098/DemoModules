package com.example.demomodules.featurehome.dataremote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
@MustBeDocumented
annotation class ProductBaseUrl

@Retention(AnnotationRetention.BINARY)
@Qualifier
@MustBeDocumented
annotation class ProductRetrofit

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
internal interface DataRemoteModule {
  companion object {
    @Provides
    @Singleton
    fun productApiService(@ProductRetrofit retrofit: Retrofit): ProductApiService =
      ProductApiService(retrofit)

    @Provides
    @ProductBaseUrl
    fun productBaseUrl(): String = "https://fakestoreapi.com"

    @Provides
    @Singleton
    @ProductRetrofit
    fun retrofit(
      @ProductBaseUrl baseUrl: String,
      client: OkHttpClient,
      moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit {
      return Retrofit.Builder()
        .client(client)
        .addConverterFactory(moshiConverterFactory)
        .baseUrl(baseUrl)
        .build()
    }
  }
}
