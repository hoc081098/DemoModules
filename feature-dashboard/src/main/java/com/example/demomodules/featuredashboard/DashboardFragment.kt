package com.example.demomodules.featuredashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.demomodules.featuredashboard.databinding.FragmentDashboardBinding
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
  private val dashboardViewModel by viewModels<DashboardViewModel>()
  private val binding by viewBinding<FragmentDashboardBinding>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    dashboardViewModel.text.observe(viewLifecycleOwner) {
      binding.textDashboard.text = it
    }
    binding.buttonToSecond.setOnClickListener {
      findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToDashboardSecondFragment())
    }
  }
}
