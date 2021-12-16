package com.example.demomodules

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

interface AppCoroutineScope : CoroutineScope

internal class DefaultAppCoroutineScope @Inject constructor(
  appCoroutineDispatchers: AppCoroutineDispatchers,
) : AppCoroutineScope {
  override val coroutineContext = appCoroutineDispatchers.io + SupervisorJob()

  override fun toString() = "DefaultAppCoroutineScope(coroutineContext=$coroutineContext)"
}
