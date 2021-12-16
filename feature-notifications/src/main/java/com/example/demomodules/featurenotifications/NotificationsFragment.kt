package com.example.demomodules.featurenotifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.demomodules.featurenotifications.databinding.FragmentNotificationsBinding
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsFragment : Fragment(R.layout.fragment_notifications) {
  private val notificationsViewModel by viewModels<NotificationsViewModel>()
  private val binding by viewBinding<FragmentNotificationsBinding>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    notificationsViewModel.text.observe(viewLifecycleOwner) {
      binding.textNotifications.text = it
    }
    binding.textNotifications.setOnClickListener {
      findNavController().navigate(NotificationsFragmentDirections.actionNotificationsFragmentToNotificationDetailFragment())
    }
  }
}
