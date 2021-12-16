package com.example.demomodules.featurenotifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.demomodules.featurenotifications.databinding.FragmentNotificationDetailBinding
import com.hoc081098.viewbindingdelegate.viewBinding

class NotificationDetailFragment : Fragment(R.layout.fragment_notification_detail) {
  private val binding by viewBinding<FragmentNotificationDetailBinding>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.root
  }
}
