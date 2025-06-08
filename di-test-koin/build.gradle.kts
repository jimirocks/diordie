plugins {
    kotlin("jvm") version "2.1.21"
    id("rocks.jimi.diordie.application-convention")
}

group = "rocks.jimi.diordie"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.insert-koin:koin-bom:4.0.4"))
    implementation("io.insert-koin:koin-core")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
application {
    mainClass.set("rocks.jimi.diordie.koin.KoinDiTestKt")
}
