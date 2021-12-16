package com.example.demomodules

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Dispatchers.Unconfined
import javax.inject.Inject

interface AppCoroutineDispatchers {
  val main: CoroutineDispatcher
  val io: CoroutineDispatcher
  val default: CoroutineDispatcher
  val unconfined: CoroutineDispatcher
}

internal class DefaultAppCoroutineDispatchers @Inject constructor() : AppCoroutineDispatchers {
  override val main: CoroutineDispatcher = Main
  override val io: CoroutineDispatcher = IO
  override val default: CoroutineDispatcher = Default
  override val unconfined: CoroutineDispatcher = Unconfined
}
