plugins {
    kotlin("jvm") version "1.9.23"
    id("me.champeau.jmh") version "0.7.2"
}

group = "org.crispin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.26.0")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

jmh {
    threads = 1
    fork = 1
    warmupIterations = 1
    iterations = 1
}

