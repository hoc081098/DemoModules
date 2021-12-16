package com.example.demomodules.featuredashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.demomodules.featuredashboard.databinding.FragmentDashboardSecondBinding
import com.hoc081098.viewbindingdelegate.viewBinding

class DashboardSecondFragment : Fragment(R.layout.fragment_dashboard_second) {
  private val binding by viewBinding<FragmentDashboardSecondBinding>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.root
  }
}
