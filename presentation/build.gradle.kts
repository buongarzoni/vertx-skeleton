plugins {
    kotlin("jvm") version "1.9.0"
}

group = "org.buongarzoni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val vertxVersion = "4.4.5"
dependencies {
    implementation(project(":model"))
    implementation("io.vertx:vertx-core:$vertxVersion")
    implementation("io.vertx:vertx-web:$vertxVersion")
    implementation("io.vertx:vertx-lang-kotlin:$vertxVersion")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:$vertxVersion")

    testImplementation("io.vertx:vertx-web-client:$vertxVersion")
    testImplementation(project(":testing"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}