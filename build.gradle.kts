plugins {
    id("java")
}

group = "global.goit"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.slf4j:slf4j-log4j12:2.0.5")
}

tasks.test {
    useJUnitPlatform()
}