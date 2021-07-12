import org.gradle.api.artifacts.dsl.DependencyHandler

/**
Created By Aunt Htoo Aung on 02/07/2021.
 **/

fun DependencyHandler.ktor() {
  implementation(KtorDep.android)
  implementation(KtorDep.jvm)
  implementation(KtorDep.ktx)
  implementation(KtorDep.log)
}

object KtorDep {

  private const val version = "1.6.1"

  // Ktor for Android
  const val android = "io.ktor:ktor-client-android:$version"

  // Ktor JVM(necessary for logging extension)
  const val jvm = "io.ktor:ktor-client-logging-jvm:$version"

  // Ktor Logging extension
  const val ktx = "io.ktor:ktor-client-logging:$version"

  // Necessary to show logs in logcat
  const val log = "ch.qos.logback:logback-classic:1.2.3"
}