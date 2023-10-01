plugins {
    kotlin("jvm") version "1.9.0"
}

group = "org.buongarzoni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":model"))
    implementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}