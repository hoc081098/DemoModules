@file:Suppress("ClassName", "SpellCheckingInspection", "MemberVisibilityCanBePrivate", "SimpleDateFormat")

import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec as PDsS
import org.gradle.plugin.use.PluginDependencySpec as PDS
import java.text.SimpleDateFormat
import java.util.Date
import org.gradle.kotlin.dsl.project

object appConfig {
  const val applicationId = "com.example.demomodules"

  private const val MAJOR = 0
  private const val MINOR = 0
  private const val PATCH = 1
  const val versionCode = MAJOR * 10_000 + MINOR * 100 + PATCH
  val versionName by lazy {
    val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
    "$MAJOR.$MINOR.$PATCH-SNAPSHOT $date"
  }

  val supportedLocales = setOf("en")
}

object versions {
  const val ktLint = "0.43.2"

  object sdk {
    const val buildTools = "31.0.0"
    const val compile = 31
    const val min = 23
    const val target = 31
  }

  object kotlin {
    const val core = "1.5.31"
    const val coroutines = "1.5.2"
  }

  object androidX {
    const val activity = "1.4.0"
    const val appCompat = "1.4.0"
    const val core = "1.7.0"
    const val fragment = "1.4.0-alpha10"
    const val startUp = "1.1.0"
    const val splashScreen = "1.0.0-alpha02"

    object view {
      const val constraintLayout = "2.1.2"
      const val material = "1.4.0"
      const val recyclerView = "1.3.0-alpha01"
    }

    const val navigation = "2.4.0-beta02"
    const val lifecycle = "2.4.0"
    const val room = "2.4.0"
    const val work = "2.7.0"
  }

  const val dagger = "2.40.2"
  const val moshiKotlin = "1.12.0"
  const val retrofit = "2.9.0"
  const val okHttpLoggingInterceptor = "5.0.0-alpha.2"
  const val leakCanaryAndroid = "2.7"
  const val rxRelay = "3.0.1"
  const val rxBinding = "4.0.0"
  const val timber = "5.0.1"

  object reactiveX {
    const val kotlin = "3.0.1"
    const val java = "3.1.2"
    const val android = "3.0.0"
  }

  const val glide = "4.12.0"

  object firebase {
    const val bom = "28.4.2"
  }
  const val viewBindingDelegate = "1.2.0"
}

object deps {
  object module {
    val DependencyHandler.core: ProjectDependency get() = project(":core")
    val DependencyHandler.domain: ProjectDependency get() = project(":domain")
    val DependencyHandler.featureHomePresentation: ProjectDependency get() = project(":feature-home:presentation")
    val DependencyHandler.featureHomeDataRemote: ProjectDependency get() = project(":feature-home:data-remote")
    val DependencyHandler.featureHomeData: ProjectDependency get() = project(":feature-home:data")
    val DependencyHandler.featureDashboard: ProjectDependency get() = project(":feature-dashboard")
    val DependencyHandler.featureNotifications: ProjectDependency get() = project(":feature-notifications")
  }

  object kotlin {
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${versions.kotlin.coroutines}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${versions.kotlin.coroutines}"
    const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${versions.kotlin.coroutines}"
    const val coroutinesRx3 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx3:${versions.kotlin.coroutines}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${versions.kotlin.core}"
  }

  object androidX {
    const val activity = "androidx.activity:activity-ktx:${versions.androidX.activity}"
    const val appCompat = "androidx.appcompat:appcompat:${versions.androidX.appCompat}"
    const val core = "androidx.core:core-ktx:${versions.androidX.core}"
    const val fragment = "androidx.fragment:fragment-ktx:${versions.androidX.fragment}"
    const val startUp = "androidx.startup:startup-runtime:${versions.androidX.startUp}"
    const val splashScreen = "androidx.core:core-splashscreen:${versions.androidX.splashScreen}"

    object view {
      const val constraintLayout = "androidx.constraintlayout:constraintlayout:${versions.androidX.view.constraintLayout}"
      const val material = "com.google.android.material:material:${versions.androidX.view.material}"
      const val recyclerView = "androidx.recyclerview:recyclerview:${versions.androidX.view.recyclerView}"
    }

    object navigation {
      const val fragment = "androidx.navigation:navigation-fragment-ktx:${versions.androidX.navigation}"
      const val ui = "androidx.navigation:navigation-ui-ktx:${versions.androidX.navigation}"
    }

    object lifecycle {
      const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${versions.androidX.lifecycle}"
      const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${versions.androidX.lifecycle}"
      const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${versions.androidX.lifecycle}"
      const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${versions.androidX.lifecycle}"
    }

    object room {
      const val runtime = "androidx.room:room-runtime:${versions.androidX.room}"
      const val ktx = "androidx.room:room-ktx:${versions.androidX.room}"
      const val rxJava3 = "androidx.room:room-rxjava3:${versions.androidX.room}"
      const val compiler = "androidx.room:room-compiler:${versions.androidX.room}"
    }

    object work {
      const val runtimeKtx = "androidx.work:work-runtime-ktx:${versions.androidX.work}"  // Kotlin + coroutines
    }
  }

  object daggerHilt {
    const val android = "com.google.dagger:hilt-android:${versions.dagger}"
    const val core = "com.google.dagger:hilt-core:${versions.dagger}"
    const val compiler = "com.google.dagger:hilt-compiler:${versions.dagger}"
  }

  object dagger {
    const val core = "com.google.dagger:dagger:${versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${versions.dagger}"
  }

  const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${versions.moshiKotlin}"

  object retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${versions.retrofit}"
  }

  const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${versions.okHttpLoggingInterceptor}"
  const val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${versions.leakCanaryAndroid}"
  const val rxRelay = "com.jakewharton.rxrelay3:rxrelay:${versions.rxRelay}"

  object rxBinding {
    const val platform = "com.jakewharton.rxbinding4:rxbinding:${versions.rxBinding}"
    const val core = "com.jakewharton.rxbinding4:rxbinding-core:${versions.rxBinding}"
    const val material = "com.jakewharton.rxbinding4:rxbinding-material:${versions.rxBinding}"
    const val swipeRefreshLayout = "com.jakewharton.rxbinding4:rxbinding-swiperefreshlayout:${versions.rxBinding}"
    const val recyclerView = "com.jakewharton.rxbinding4:rxbinding-recyclerview:${versions.rxBinding}"
  }

  const val timber = "com.jakewharton.timber:timber:${versions.timber}"

  object reactiveX {
    const val kotlin = "io.reactivex.rxjava3:rxkotlin:${versions.reactiveX.kotlin}"
    const val java = "io.reactivex.rxjava3:rxjava:${versions.reactiveX.java}"
    const val android = "io.reactivex.rxjava3:rxandroid:${versions.reactiveX.android}"
  }

  object glide {
    const val glide = "com.github.bumptech.glide:glide:${versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${versions.glide}"
    const val integration = "com.github.bumptech.glide:okhttp3-integration:${versions.glide}"
  }

  object firebase {
    val DependencyHandler.bom get() = platform("com.google.firebase:firebase-bom:${versions.firebase.bom}")
    const val auth = "com.google.firebase:firebase-auth-ktx"
    const val storage = "com.google.firebase:firebase-storage-ktx"
    const val firestore = "com.google.firebase:firebase-firestore-ktx"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val config = "com.google.firebase:firebase-config-ktx"
  }

  const val viewBindingDelegate = "com.github.hoc081098:ViewBindingDelegate:${versions.viewBindingDelegate}"
}

inline val PDsS.androidApp: PDS get() = id("com.android.application")
inline val PDsS.androidLib: PDS get() = id("com.android.library")
inline val PDsS.kotlinAndroid: PDS get() = id("kotlin-android")
inline val PDsS.kotlinKapt: PDS get() = id("kotlin-kapt")
inline val PDsS.navSafeArgsKotlin: PDS get() = id("androidx.navigation.safeargs.kotlin")
inline val PDsS.daggerHiltAndroid: PDS get() = id("dagger.hilt.android.plugin")
