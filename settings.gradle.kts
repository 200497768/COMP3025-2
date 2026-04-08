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
plugins {
<<<<<<< HEAD
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
=======
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
>>>>>>> 2753dbfe85125259a04d9c600da0308abe4148f9
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "COMP3025 2"
include(":app")
