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
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        google()
        mavenCentral()
        google()
    }
}

rootProject.name = "ShiftAppIntern"
include(":app")
include(":core:common")
include(":core:network")
include(":core:designsystem")
include(":core:model")
include(":feature:catalog")
include(":feature:pizzacard")
