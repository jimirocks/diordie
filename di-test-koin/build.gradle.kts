plugins {
    kotlin("jvm") version "2.0.21"
    id("com.google.devtools.ksp") version "2.0.21-1.0.28"
}

group = "rocks.jimi.diordie"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.insert-koin:koin-bom:4.0.4"))
    implementation("io.insert-koin:koin-core")
    implementation("io.insert-koin:koin-annotations:2.0.0")
    ksp("io.insert-koin:koin-ksp-compiler:2.0.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
