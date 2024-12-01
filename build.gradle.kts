plugins {
    kotlin("jvm") version "2.0.21"
    application
}

version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

tasks.register<JavaExec>("task01") {
    //dependsOn "classes"
    group = "application"
    description = "task 1"
    mainClass = "year2024.task01.MainKt"
    classpath = sourceSets.main.get().runtimeClasspath
}
