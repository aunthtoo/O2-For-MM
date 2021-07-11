import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
  id("com.android.application")
  id("kotlin-android")
}

val localProperties = loadProperties(File(rootDir, "local.properties").path)

android {
  compileSdk = BuildConfig.compileSdk

  defaultConfig {
    applicationId = "io.github.o2formm"
    minSdk = BuildConfig.minSdk
    targetSdk = BuildConfig.targetSdk
    versionCode = BuildConfig.versionCode
    versionName = BuildConfig.versionName

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    multiDexEnabled = true

    buildFeatures {
      viewBinding = true
    }

    buildConfigField("String", "GOOGLE_API_KEY", localProperties.getProperty("GOOGLE_API_KEY"))
    buildConfigField("String", "SPREAD_SHEET_ID", localProperties.getProperty("SPREAD_SHEET_ID"))
    buildConfigField(
      "String",
      "SERVICES_SHEET_ID",
      localProperties.getProperty("SERVICES_SHEET_ID")
    )
    buildConfigField(
      "String",
      "VALIDATION_SHEET_ID",
      localProperties.getProperty("VALIDATION_SHEET_ID")
    )
    buildConfigField("String", "PCODE_SHEET_ID", localProperties.getProperty("PCODE_SHEET_ID"))
  }

  buildTypes {

    debug {
      isMinifyEnabled = false
    }

    release {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    isCoreLibraryDesugaringEnabled = true
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }

  packagingOptions {
    exclude("META-INF/DEPENDENCIES")
  }
}

dependencies {

  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(project(":androidextensions"))

  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

  coreLibraryDesugaring(CommonLibs.desugar_lib)

  implementation(Kotlin.stdblib_jdk)
  implementation(KotlinCoroutine.android)

  //AndroidX
  implementation(AndroidXAppCompat.app_compat)
  implementation(AndroidXCore.core_ktx)
  implementation(AndroidXActivity.activity_ktx)
  implementation(AndroidXFragment.fragment_ktx)
  androidxFragment()
  androidXArch()
  implementation(AndroidXViewPager.view_pager_2)
  implementation(AndroidXViewPager.view_pager)
  implementation(AndroidXPreference.preference_ktx)
  implementation(AndroidXPaging.common)
  implementation(AndroidXPaging.runtime)
  implementation(AndroidXLocalBroadcastManager.local_broadcast_manager)
  implementation("androidx.exifinterface:exifinterface:1.3.2")

  //Work Manager
  implementation(AndroidXWork.work_runtime_ktx)
  androidTestImplementation(AndroidXWork.work_testing)

  //Material
  implementation(Material.material)

  //swipe refresh layout
  implementation(AndroidXSwipeRefreshLayout.swipe_refresh_layout)

  //Constraint Layout
  implementation(AndroidXConstraintLayout.constraint_layout)

  //multi dex
  implementation(AndroidXMultiDex.multi_dex)

  //timber
  implementation(CommonLibs.timber)

  //dexter
  implementation(CommonLibs.dexter)

  //Coil
  implementation(Coil.coil)

  //Test
  testImplementation(CommonLibs.junit)
  androidXTest()
  androidXEspresso()

  //koin for di
  koinAndroid()

  //for google sheet
  implementation("com.google.apis:google-api-services-sheets:v4-rev612-1.25.0")
  implementation("com.google.api-client:google-api-client-android:1.31.5")
}