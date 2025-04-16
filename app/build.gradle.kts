val springFrameworkVersion = "6.2.5"
val junitJupiterVersion = "5.12.1"
val springDataVersion = "3.4.2"
val thymeleafVersion = "3.1.3.RELEASE"
val postgresqlVersion = "42.7.5"
val jooqVersion = "3.20.3"
val flywayCore = "11.6.0"
val jakartaServletApiVersion = "6.1.0"
val hamcrestVersion = "3.0"
val mockitoVersion = "5.17.0"

group = "org.myblog"
version = "0.0.1-SNAPSHOT"

plugins {
    java
    war
    id("io.freefair.lombok") version "8.13.1"
    id("java-test-fixtures")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework:spring-webmvc:$springFrameworkVersion")
    implementation("org.springframework.data:spring-data-jdbc:$springDataVersion")

    implementation("org.flywaydb:flyway-core:$flywayCore")
    implementation("org.jooq:jooq:$jooqVersion")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("com.h2database:h2:2.3.232")

    implementation("org.thymeleaf:thymeleaf:$thymeleafVersion")
    implementation("org.thymeleaf:thymeleaf-spring6:$thymeleafVersion")

    implementation("jakarta.servlet:jakarta.servlet-api:$jakartaServletApiVersion")

    testImplementation(platform("org.junit:junit-bom:$junitJupiterVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.hamcrest:hamcrest:$hamcrestVersion")

    testImplementation("org.mockito:mockito-junit-jupiter:$mockitoVersion")

    testImplementation("org.springframework:spring-test:$springFrameworkVersion")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
