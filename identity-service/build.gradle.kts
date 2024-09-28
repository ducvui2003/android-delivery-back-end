plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.diffplug.spotless") version "6.22.0"
}

group = "com.spring"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val springCloudVersion: String by extra("2023.0.2")


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

ext {
    set("springCloudVersion", "2023.0.2")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    runtimeOnly("com.mysql:mysql-connector-j")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

//    OAuth
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-client")
    implementation("org.springframework.security:spring-security-oauth2-client")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

    // https://mvnrepository.com/artifact/com.google.api-client/google-api-client
    implementation("com.google.api-client:google-api-client:2.6.0")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("com.google.firebase:firebase-admin:9.3.0")
    // https://mvnrepository.com/artifact/com.googlecode.libphonenumber/libphonenumber
    implementation("com.googlecode.libphonenumber:libphonenumber:8.12.56")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}")
    }
}
spotless {
    java {
        // Target only Java files in src/main/java
        target("src/main/java/**/*.java");
//        palantirJavaFormat();
        removeUnusedImports();
        toggleOffOn();
        trimTrailingWhitespace();
        indentWithTabs(4);
        importOrder("java", "javax", "org", "com", "com.diffplug");
    }
}
tasks.withType<Test> {
    useJUnitPlatform()
}
