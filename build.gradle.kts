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

for (i in 1..2) {
    val taskName = "task${i.toString().padStart(2,'0')}"
    tasks.register<JavaExec>(taskName) {
        group = "application"
        description = taskName
        mainClass = "year2024.$taskName.MainKt"
        classpath = sourceSets.main.get().runtimeClasspath
    }
}