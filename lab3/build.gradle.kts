plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    implementation("org.seleniumhq.selenium:selenium-java:4.19.1")
}

tasks.test {
    useJUnitPlatform()
}
