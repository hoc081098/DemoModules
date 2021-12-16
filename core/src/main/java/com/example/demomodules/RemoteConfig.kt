package com.example.demomodules

import com.example.demomodules.RemoteConfig.FeatureKey
import com.example.demomodules.core.BuildConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

interface RemoteConfig {
  enum class FeatureKey {
    HOME,
    DASHBOARD,
    NOTIFICATIONS;

    companion object {
      @JvmField
      val SIZE = values().size
    }
  }

  suspend fun initialize()

  suspend fun isFeatureEnabled(key: FeatureKey): Boolean
}

@OptIn(ExperimentalTime::class)
internal class FirebaseRemoteConfigImpl @Inject constructor(
  private val remoteConfig: FirebaseRemoteConfig,
  private val appCoroutineDispatchers: AppCoroutineDispatchers,
  appCoroutineScope: AppCoroutineScope,
) : RemoteConfig {
  private val fetchDeferred: Deferred<Unit> = appCoroutineScope.async(start = CoroutineStart.LAZY) {
    runCatching {
      val settings = remoteConfigSettings {
        minimumFetchIntervalInSeconds =
          if (BuildConfig.DEBUG) 0L else Duration.hours(4).inWholeSeconds
      }
      remoteConfig.setConfigSettingsAsync(settings).await()
      remoteConfig.fetchAndActivate().await()
      if (BuildConfig.DEBUG) {
        Timber.d("${this@FirebaseRemoteConfigImpl} all: ${remoteConfig.all.mapValues { (_, v) -> v.asString() }}")
      }
    }
  }

  override suspend fun initialize() = withContext(appCoroutineDispatchers.io) {
    fetchDeferred.await()
  }

  override suspend fun isFeatureEnabled(key: FeatureKey) = withContext(appCoroutineDispatchers.io) {
    initialize()
    remoteConfig[key.rawKey].asBoolean()
  }

  private inline val FeatureKey.rawKey: String
    get() = when (this) {
      FeatureKey.HOME -> "feature_home"
      FeatureKey.DASHBOARD -> "feature_dashboard"
      FeatureKey.NOTIFICATIONS -> "feature_notifications"
    }
}
