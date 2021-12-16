package com.example.demomodules

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demomodules.databinding.ActivityMainBinding
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

@OptIn(ExperimentalStdlibApi::class, ExperimentalTime::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  private val binding by viewBinding<ActivityMainBinding>()
  private val vm by viewModels<MainViewModel>()

  private lateinit var navController: NavController
  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val timeout = TimeSource.Monotonic.markNow() + Duration.seconds(3)
    installSplashScreen().setKeepVisibleCondition {
      val loading = vm.stateFlow.value.isLoading
      val hasNotPassedNow = timeout.hasNotPassedNow()

      Timber.tag("MainActivity").d("check $loading $hasNotPassedNow -> ${loading && hasNotPassedNow}")
      loading && hasNotPassedNow
    }
    setContentView(R.layout.activity_main)

    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
    navController = navHostFragment.navController

    val mobileNav = navController.navInflater.inflate(R.navigation.mobile_navigation)

    vm.stateFlow
      .flowWithLifecycle(lifecycle)
      .onEach { binding.bind(it, mobileNav) }
      .launchIn(lifecycleScope)
  }

  @Suppress("NOTHING_TO_INLINE")
  private inline fun buildTopLevelDestinationIds(state: MainState): Set<Int> =
    buildSet(capacity = RemoteConfig.FeatureKey.SIZE) {
      if (state.featureHomeEnabled == true) {
        add(R.id.home_fragment)
      }
      if (state.featureDashboardEnabled == true) {
        add(R.id.dashboard_fragment)
      }
      if (state.featureNotificationsEnabled == true) {
        add(R.id.notifications_fragment)
      }
    }

  @Suppress("NOTHING_TO_INLINE")
  private inline fun Menu.bind(state: MainState) {
    clear()
    menuInflater.inflate(R.menu.bottom_nav_menu, this)

    if (state.featureHomeEnabled != true) {
      removeItem(R.id.home_graph)
    }
    if (state.featureDashboardEnabled != true) {
      removeItem(R.id.dashboard_graph)
    }
    if (state.featureNotificationsEnabled != true) {
      removeItem(R.id.notifications_graph)
    }
  }

  private fun ActivityMainBinding.bind(state: MainState, mobileNav: NavGraph) {
    Timber.tag("MainActivity").d("bind $state")

    if (state.isLoading) {
      progressCircular.isVisible = true
      navView.menu.clear()
      return
    }

    progressCircular.isVisible = false

    // Attach the graph to the NavController.
    navController.graph = mobileNav

    // Setup the bottom navigation view with navController
    navView.run {
      menu.bind(state)
      setupWithNavController(navController)
    }

    // Setup the ActionBar with navController and 3 top level destinations
    appBarConfiguration = AppBarConfiguration(buildTopLevelDestinationIds(state))
    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration)
}
