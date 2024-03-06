plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    //
}

tasks.test {
    useJUnitPlatform()
}