pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "vertx-skeleton"
include("app")
include("infrastructure")
include("model")
include("testing")
include("presentation")
