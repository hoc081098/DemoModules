rootProject.name = "DemoModules"
include(":app")
include(":core")
include(":domain")
includeProject(":feature-home:presentation", "feature-home/presentation")
includeProject(":feature-home:data-remote", "feature-home/data-remote")
includeProject(":feature-home:data", "feature-home/data")
include(":feature-dashboard")
include(":feature-notifications")

fun includeProject(name: String, filePath: String) {
  include(name)
  project(name).projectDir = File(filePath)
}