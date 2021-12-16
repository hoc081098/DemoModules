plugins {
  `demo-modules-plugin`
  kotlin
  kotlinKapt
}

dependencies {
  implementation(deps.kotlin.coroutinesCore)

  implementation(deps.dagger.core)
  kapt(deps.dagger.compiler)
}
