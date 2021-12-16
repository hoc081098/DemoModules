package com.example.demomodules.initializer

import android.content.Context
import androidx.startup.Initializer
import com.example.demomodules.BuildConfig
import timber.log.Timber

@Suppress("unused")
class TimberInitializer : Initializer<Unit> {
  override fun create(context: Context) {
    if (BuildConfig.DEBUG) {
      Timber.plant(
        Timber.DebugTree(),
      )
    } else {
      // TODO: CrashlyticsLogger
    }
    Timber.tag("Initializer").d("Initialized Timber")
  }

  override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
