import org.gradle.api.artifacts.dsl.DependencyHandler

/**
Created By Aunt Htoo Aung on 02/07/2021.
 **/

fun DependencyHandler.koinAndroid() {
  implementation(KoinDep.android)
  implementation(KoinDep.scope)
  implementation(KoinDep.viewmodel)
}

fun DependencyHandler.koinCore() {
  implementation(KoinDep.core)
  testImplementation(KoinDep.test)
}

object KoinDep {

  private const val version = "2.0.1"

  const val core = "org.koin:koin-core:$version"
  const val test = "org.koin:koin-test:$version"

  const val android = "org.koin:koin-android:$version"
  const val scope = "org.koin:koin-androidx-scope:$version"
  const val viewmodel = "org.koin:koin-androidx-viewmodel:$version"

}