plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.buongarzoni"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val vertxVersion = "4.4.5"
dependencies {
    implementation(project(":model"))
    implementation(project(":infrastructure"))
    implementation(project(":presentation"))
    implementation("io.vertx:vertx-core:$vertxVersion")
    implementation("io.vertx:vertx-web:$vertxVersion")
    implementation("io.vertx:vertx-lang-kotlin:$vertxVersion")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:$vertxVersion")

    testImplementation("io.vertx:vertx-web-client:$vertxVersion")
    testImplementation(kotlin("test"))
    testImplementation(project(":testing"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("app.jar")
}

kotlin {
    jvmToolchain(19)
}

application {
    mainClass.set("MainKt")
}