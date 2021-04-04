import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val artifactoryRepository: String by project
val artifactoryUser: String by project
val artifactoryPassword: String by project

repositories {
	maven {
		setUrl(artifactoryRepository)
		credentials {
			username = artifactoryUser
			password = artifactoryPassword
		}
	}
	mavenLocal()
}

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"

}

group = "br.com.fiap"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

sourceSets {
	create("integrationTest") {
		compileClasspath += sourceSets.main.get().output
		runtimeClasspath += sourceSets.main.get().output
	}
}
val integrationTestImplementation: Configuration by configurations.getting {
	extendsFrom(configurations.implementation.get())
}

configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("io.azam.ulidj:ulidj:1.0.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("io.mockk:mockk:1.9.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}

	testImplementation("com.ninja-squad:springmockk:2.0.3")
	integrationTestImplementation(sourceSets["test"].output)
	integrationTestImplementation("org.junit.jupiter:junit-jupiter")
	integrationTestImplementation("org.springframework.boot:spring-boot-starter-test")
	integrationTestImplementation("org.springframework.kafka:spring-kafka-test")
	integrationTestImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
	integrationTestImplementation("io.mockk:mockk:1.9.1")
	integrationTestImplementation("org.awaitility:awaitility-kotlin:3.1.6")

}


tasks.test {
	finalizedBy("integrationTest")
}

val integrationTestTask = tasks.create("integrationTest", Test::class) {
	description = "Runs the integration tests."
	group = "verification"

	testClassesDirs = sourceSets["integrationTest"].output.classesDirs
	classpath = sourceSets["integrationTest"].runtimeClasspath
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
