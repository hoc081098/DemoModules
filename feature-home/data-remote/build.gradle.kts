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
  implementation(deps.module.run { core })
  implementation(deps.module.run { domain })

  implementation(deps.kotlin.coroutinesCore)

  implementation(deps.moshiKotlin)
  implementation(deps.okHttpLoggingInterceptor)
  implementation(deps.retrofit.retrofit)
  implementation(deps.retrofit.converterMoshi)

  implementation(deps.daggerHilt.android)
  kapt(deps.daggerHilt.compiler)
}
