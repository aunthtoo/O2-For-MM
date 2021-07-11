import org.gradle.api.artifacts.dsl.DependencyHandler

/**
Created By Aunt Htoo Aung on 03/07/2021.
 **/
fun DependencyHandler.jsoup() {
  implementation(JsoupDep.jsoup)
}

object JsoupDep {

  private const val version = "1.13.1"

  const val jsoup = "org.jsoup:jsoup:$version"
}