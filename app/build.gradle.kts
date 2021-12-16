plugins {
  `demo-modules-plugin`
  androidApp
  kotlinAndroid
  navSafeArgsKotlin
  kotlinKapt
  daggerHiltAndroid
}

hilt {
  enableAggregatingTask = true
}

demoModulesApp {
  viewBinding = true
  parcelize = true
}

dependencies {
  implementation(deps.module.run { core })
  implementation(deps.module.run { featureHomePresentation })
  implementation(deps.module.run { featureHomeData })
  implementation(deps.module.run { featureDashboard })
  implementation(deps.module.run { featureNotifications })

  implementation(deps.androidX.core)
  implementation(deps.androidX.appCompat)
  implementation(deps.androidX.splashScreen)
  implementation(deps.androidX.startUp)
  implementation(deps.androidX.view.material)
  implementation(deps.androidX.view.constraintLayout)

  implementation(deps.androidX.lifecycle.liveData)
  implementation(deps.androidX.lifecycle.viewModel)
  implementation(deps.androidX.lifecycle.runtime)

  implementation(deps.androidX.navigation.fragment)
  implementation(deps.androidX.navigation.ui)

  implementation(deps.viewBindingDelegate)
  implementation(deps.timber)

  implementation(deps.daggerHilt.android)
  kapt(deps.daggerHilt.compiler)
}

apply {
  plugin("com.google.gms.google-services")
}
