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
    implementation("org.jetbrains.exposed:exposed:0.17.14")
    implementation("org.postgresql:postgresql:42.3.8")

    testImplementation(kotlin("test"))
    testImplementation(project(":testing"))
}

tasks.test {
    useJUnitPlatform()
}