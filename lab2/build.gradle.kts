plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation ("org.junit.jupiter:junit-jupiter-params:5.1.0")

    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("org.mockito:mockito-inline:2.8.47")

    implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.5.0")
}

tasks.test {
    useJUnitPlatform()
}