val springFrameworkVersion = "6.2.5"
val junitJupiterVersion = "5.12.1"
val springDataVersion = "3.4.2"
val thymeleafVersion = "3.1.3.RELEASE"

plugins {
    java
    application
    id("io.freefair.lombok") version "8.13.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-context:$springFrameworkVersion")
    implementation("org.springframework:spring-webmvc:$springFrameworkVersion")
    implementation("org.springframework.data:spring-data-jdbc:$springDataVersion")

    implementation("org.thymeleaf:thymeleaf:$thymeleafVersion")
    implementation("org.thymeleaf:thymeleaf-spring6:$thymeleafVersion")

    testImplementation(platform("org.junit:junit-bom:$junitJupiterVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.springframework:spring-test:$springFrameworkVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "org.myblog.App"
}
