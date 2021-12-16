package com.example.demomodules.featurehome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demomodules.domain.model.Product
import com.example.demomodules.domain.usecase.GetProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

sealed interface HomeIntent {
  object Initial : HomeIntent
  object Retry : HomeIntent
}

data class HomeState(
  val products: List<Product>,
  val isLoading: Boolean,
  val error: Throwable?
) {
  companion object {
    fun initial(): HomeState = HomeState(
      products = emptyList(),
      isLoading = true,
      error = null,
    )
  }
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
  private val getProducts: GetProducts,
) : ViewModel() {
  private val state = MutableStateFlow(HomeState.initial())
  private val intents = MutableSharedFlow<HomeIntent>(extraBufferCapacity = 64)

  val stateFlow: StateFlow<HomeState> = state.asStateFlow()
  suspend fun dispatch(intent: HomeIntent) = intents.emit(intent)

  init {
    merge(
      intents
        .filterIsInstance<HomeIntent.Initial>()
        .filter { state.value.products.isEmpty() },
      intents
        .filterIsInstance<HomeIntent.Retry>()
        .filter { state.value.error != null }
    )
      .flatMapLatest {
        getProducts::invoke.asFlow()
          .map { products ->
            state.update {
              HomeState(
                isLoading = false,
                products = products,
                error = null,
              )
            }
          }
          .onStart { state.update { it.copy(isLoading = true, error = null) } }
          .catch { error -> state.update { it.copy(isLoading = false, error = error) } }
      }
      .launchIn(viewModelScope)
  }
}
