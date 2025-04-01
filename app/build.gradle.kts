plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.11.3")
        }
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
