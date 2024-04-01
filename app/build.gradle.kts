import ScriptUtils.printHello

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.2")

    // Test
    implementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.register("printHello") {
    doLast {
        printHello()
    }
}

tasks.named("build") {
    dependsOn("printHello")
}
