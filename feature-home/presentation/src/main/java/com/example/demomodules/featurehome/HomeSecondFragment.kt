package com.example.demomodules.featurehome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.demomodules.featurehome.databinding.FragmentHomeSecondBinding
import com.hoc081098.viewbindingdelegate.viewBinding

class HomeSecondFragment : Fragment(R.layout.fragment_home_second) {
  private val binding by viewBinding<FragmentHomeSecondBinding>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.root
  }
}
