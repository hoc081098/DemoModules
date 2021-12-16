package com.example.demomodules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class MainState(
  val featureHomeEnabled: Boolean?,
  val featureDashboardEnabled: Boolean?,
  val featureNotificationsEnabled: Boolean?,
  val isLoading: Boolean,
) {
  companion object {
    fun initial(): MainState = MainState(
      featureHomeEnabled = null,
      featureDashboardEnabled = null,
      featureNotificationsEnabled = null,
      isLoading = true,
    )
  }
}

@OptIn(FlowPreview::class)
@HiltViewModel
class MainViewModel @Inject constructor(
  private val remoteConfig: RemoteConfig,
) : ViewModel() {
  val stateFlow: StateFlow<MainState> = combine(
    suspend { remoteConfig.isFeatureEnabled(RemoteConfig.FeatureKey.HOME) }.asFlow(),
    suspend { remoteConfig.isFeatureEnabled(RemoteConfig.FeatureKey.DASHBOARD) }.asFlow(),
    suspend { remoteConfig.isFeatureEnabled(RemoteConfig.FeatureKey.NOTIFICATIONS) }.asFlow(),
  ) { a, b, c ->
    MainState(
      featureHomeEnabled = a,
      featureDashboardEnabled = b,
      featureNotificationsEnabled = c,
      isLoading = false,
    )
  }.stateIn(
    viewModelScope,
    SharingStarted.Lazily,
    MainState.initial(),
  )
}
