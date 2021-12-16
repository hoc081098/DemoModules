package com.example.demomodules.featurehome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.demomodules.featurehome.databinding.FragmentHomeBinding
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

  private val homeViewModel by viewModels<HomeViewModel>()
  private val binding by viewBinding<FragmentHomeBinding>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    homeViewModel
      .stateFlow
      .flowWithLifecycle(viewLifecycleOwner.lifecycle)
      .onEach { binding.bind(it) }
      .launchIn(viewLifecycleOwner.lifecycleScope)

    lifecycleScope.launch { homeViewModel.dispatch(HomeIntent.Initial) }

    binding.buttonToSecond.setOnClickListener {
      findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment())
    }
  }

  private fun FragmentHomeBinding.bind(state: HomeState) {
    textHome.text = buildString {
      append("products.size=")
      append(state.products.size)
      append(",isLoading=")
      append(state.isLoading)
      append(",error=")
      append(state.error?.message)
    }
  }
}
