plugins {
    java
    checkstyle
    jacoco
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    // id("org.liquibase.gradle") version "2.2.0"
}

//liquibase {
//    activities {
//        register("migrate") {
//        }
//    }
//    runList = "migrate"
//}

checkstyle {
    toolVersion = "10.13.0"
    isIgnoreFailures = false
    maxWarnings = 10000
    maxErrors = 10000
}

group = "hse"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation("org.springframework.boot:spring-boot-starter-web")

    runtimeOnly("org.postgresql:postgresql")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.boot:spring-boot-starter-aop")

    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("org.glassfish.expressly:expressly:5.0.0")

    // Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    // Для работы с Kafka в Spring Boot
    implementation("org.springframework.kafka:spring-kafka")
    // Для подключения к брокеру Kafka
    implementation("org.apache.kafka:kafka-clients")
    testImplementation("org.springframework.kafka:spring-kafka-test")

    // Repository

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    // implementation("org.postgresql:postgresql")
    implementation("org.postgresql:postgresql:42.7.3")
    //liquibaseRuntime("org.postgresql:postgresql:42.7.3")
    //implementation("org.liquibase:liquibase-core:4.29.2")
    //liquibaseRuntime("org.liquibase:liquibase-core:4.29.2")
    //liquibaseRuntime("org.liquibase.ext:liquibase-hibernate6:4.29.2")
    //liquibaseRuntime("org.liquibase:liquibase-groovy-dsl:2.1.1")
    //liquibaseRuntime("info.picocli:picocli:4.7.5")
    //liquibaseRuntime("org.yaml:snakeyaml:1.33")
    //liquibaseRuntime("mysql:mysql-connector-java:5.1.34")
    //liquibaseRuntime("org.hibernate:hibernate-core:6.1.1.Final")
    implementation("com.databricks:databricks-jdbc:2.6.36")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
        html.required = true
    }
}
