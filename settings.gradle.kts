pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }

}
dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        google()
    }
}
rootProject.name = "ShiftAppIntern"
include(":app")
include(":core:common")
include(":core:network")
include(":core:designsystem")
include(":core:domain")
include(":core:data")
include(":feature:catalog")
include(":feature:pizzacard")
include(":feature:cart")
include(":feature:temp")
