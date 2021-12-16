plugins {
  `demo-modules-plugin`
  androidLib
  kotlinAndroid
  kotlinKapt
  daggerHiltAndroid
}

hilt {
  enableAggregatingTask = true
}

dependencies {
  implementation(deps.androidX.core)
  implementation(deps.androidX.appCompat)
  implementation(deps.androidX.view.material)

  implementation(deps.firebase.config)
  implementation(deps.firebase.run { bom })

  implementation(deps.daggerHilt.android)
  kapt(deps.daggerHilt.compiler)

  implementation(deps.moshiKotlin)
  implementation(deps.okHttpLoggingInterceptor)
  implementation(deps.retrofit.converterMoshi)

  implementation(deps.kotlin.coroutinesCore)
  implementation(deps.kotlin.coroutinesAndroid)
  implementation(deps.kotlin.coroutinesPlayServices)

  implementation(deps.timber)
}
