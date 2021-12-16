plugins {
  `demo-modules-plugin`
  androidLib
  kotlinAndroid
  navSafeArgsKotlin
  kotlinKapt
  daggerHiltAndroid
}

demoModulesApp {
  viewBinding = true
  parcelize = true
}

hilt {
  enableAggregatingTask = true
}

dependencies {
  implementation(deps.module.run { core })

  implementation(deps.androidX.core)
  implementation(deps.androidX.appCompat)
  implementation(deps.androidX.view.material)
  implementation(deps.androidX.view.constraintLayout)

  implementation(deps.androidX.lifecycle.liveData)
  implementation(deps.androidX.lifecycle.viewModel)

  implementation(deps.androidX.navigation.fragment)
  implementation(deps.androidX.navigation.ui)

  implementation(deps.viewBindingDelegate)
  implementation(deps.daggerHilt.android)
  kapt(deps.daggerHilt.compiler)
}
