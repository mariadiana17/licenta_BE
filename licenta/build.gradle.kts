plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.jfree:jfreechart:1.5.3")
	runtimeOnly("com.h2database:h2")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.2.5")
	compileOnly("org.projectlombok:lombok:1.18.24")
	runtimeOnly("org.postgresql:postgresql:42.2.23")
	annotationProcessor ("org.projectlombok:lombok:1.18.24")
	implementation ("org.jpmml:pmml-evaluator-metro:1.6.4")
	implementation ("org.jpmml:pmml-model:1.3.9")
	implementation ("org.jpmml:pmml-schema:1.3.9")
	implementation ("javax.xml.bind:jaxb-api:2.3.0")
	implementation ("org.glassfish.jaxb:jaxb-runtime:2.3.0")
	implementation ("org.glassfish.jaxb:jaxb-core:4.0.5")
	implementation ("javax.activation:javax.activation-api:1.2.0")
	implementation ("com.sun.xml.bind:jaxb-impl:2.2.11")
	implementation ("com.sun.xml.bind:jaxb-core:2.2.11")
	implementation("org.pmml4s:pmml4s_3:1.0.1")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
