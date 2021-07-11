// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    classpath(CommonLibs.android_gradle_plugin)
    classpath(Kotlin.gradle_plugin)

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle.kts files
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}