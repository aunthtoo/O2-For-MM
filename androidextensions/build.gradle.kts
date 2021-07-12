plugins {
  id("com.android.library")
  kotlin("android")
}

android {
  compileSdk = BuildConfig.compileSdk

  defaultConfig {
    minSdk = BuildConfig.minSdk
    targetSdk = BuildConfig.targetSdk

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    consumerProguardFiles("consumer-rules.pro")

    buildFeatures {
      viewBinding = true
    }

    multiDexEnabled = true
  }

  buildTypes {

    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  coreLibraryDesugaring(CommonLibs.desugar_lib)

  implementation(Kotlin.stdblib_jdk)

  //AndroidX
  implementation(AndroidXAppCompat.app_compat)
  implementation(AndroidXCore.core_ktx)
  implementation(Material.material)
  implementation(AndroidXRecyclerView.recycler_view)
  implementation(AndroidXActivity.activity_ktx)
  androidxFragment()
  androidXArch()

  //Timber
  implementation(CommonLibs.timber)

  //Testing
  testImplementation(CommonLibs.junit)
  mockito()
  mockitoAndroid()
  androidXTest()
}