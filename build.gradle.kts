import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
}

java.sourceCompatibility = JavaVersion.VERSION_17
allprojects {
    group = "json-handling"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

dependencyManagement {
    val springCloudDependenciesVersion: String by project
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:Greenwich.SR6")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        // Kotlin
        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }

    tasks.getByName("bootJar") {
        enabled = false
    }

    tasks.getByName("jar") {
        enabled = true
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.test {
        useJUnitPlatform()
    }
}
