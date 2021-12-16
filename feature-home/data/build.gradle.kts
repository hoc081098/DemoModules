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
  implementation(deps.module.run { featureHomeDataRemote })

  implementation(deps.kotlin.coroutinesCore)

  implementation(deps.daggerHilt.android)
  kapt(deps.daggerHilt.compiler)
}
