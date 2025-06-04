plugins {
    kotlin("jvm") version "2.1.21"
    id("org.springframework.boot") version "3.2.2"
    kotlin("plugin.spring") version "2.1.21"
}

group = "rocks.jimi.diordie"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.2"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-context")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
