package com.example.demomodules.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.demomodules.AppCoroutineScope
import com.example.demomodules.RemoteConfig
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.launch
import timber.log.Timber

@Suppress("unused")
class RemoteConfigInitializer : Initializer<Unit> {
  @EntryPoint
  @InstallIn(SingletonComponent::class)
  interface InitializerEntryPoint {
    fun remoteConfig(): RemoteConfig

    fun appCoroutineScope(): AppCoroutineScope
  }

  override fun create(context: Context) {
    val initializerEntryPoint =
      EntryPointAccessors.fromApplication<InitializerEntryPoint>(context.applicationContext)

    val remoteConfig = initializerEntryPoint.remoteConfig()
    initializerEntryPoint.appCoroutineScope().launch {
      Timber.d("Start initializing $remoteConfig")
      remoteConfig.initialize()
      Timber.d("Done $remoteConfig")
    }

    Timber.tag("Initializer").d("Initialized RemoteConfig $remoteConfig")
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
    TimberInitializer::class.java
  )
}
