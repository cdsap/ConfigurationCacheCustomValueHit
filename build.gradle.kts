plugins {
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "1.0.0-rc-1"
}

group = "io.github.cdsap"
version = "0.1.0"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}
dependencies {
    implementation("com.gradle.enterprise:com.gradle.enterprise.gradle.plugin:3.13.3")
    testImplementation("junit:junit:4.13.2")
}

gradlePlugin {
    plugins {
        create("ConfigurationCache") {
            id = "io.github.cdsap.configurationcachehit"
            displayName = "Configuration Cache Custom Value hit"
            description =
                "Extends the Build Scan with a Custom Value indicating if the configuration cache has been hit"
            implementationClass = "io.github.cdsap.configurationcachehit.ConfigurationCacheCustomValueHit"
        }
    }
}

pluginBundle {
    website = "https://github.com/cdsap/ConfigurationCacheCustomValueHit"
    vcsUrl = "https://github.com/cdsap/ConfigurationCacheCustomValueHit"
    tags = listOf("configurationcache")
}
