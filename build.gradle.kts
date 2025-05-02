plugins {
    kotlin("jvm") version "2.0.20"
    id("com.gradleup.shadow") version "9.0.0-beta4"
}

group = "io.github.mayachen350"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.exaroton:api:2.2.1")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.2")
    implementation("me.jakejmattson:DiscordKt:0.24.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    manifest {
        attributes["Main-Class"] = "io.github.mayachen350.trickMcDiscordBot.MainAppKt"
    }
}

kotlin {
    jvmToolchain(17)
}