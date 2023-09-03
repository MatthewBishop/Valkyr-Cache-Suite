/*
 * Copyright (c) 2022, Owain van Brakel <https://github.com/Owain94>
 * All rights reserved.
 *
 * This code is licensed under GPL3, see the complete license in
 * the LICENSE file in the root directory of this source tree.
 */
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java-gradle-plugin")
    kotlin("jvm") version "1.7.0"
//    `maven-publish`
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

val rlver = "1.10.10"

group = "com.openosrs"
version = "1.0.1"

javafx {
    modules("javafx.controls", "javafx.fxml")
}

repositories {
    mavenCentral()
    mavenLocal()
//    maven {
//        url = uri("https://raw.githubusercontent.com/open-osrs/hosting/master")
//    }

    exclusiveContent {
        forRepository {
            maven {
                url = uri("https://repo.runelite.net")
            }
        }
        filter {
            includeModule("net.runelite", "runelite-parent")
            includeModule("net.runelite", "cache")
        }
    }
}

dependencies {
    annotationProcessor(group = "org.projectlombok", name = "lombok", version = "1.18.20")
    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.20")

    implementation(group = "com.google.guava", name = "guava", version = "30.1.1-jre")
    implementation(group = "org.antlr", name = "antlr4-runtime", version = "4.8-1")
    implementation(group = "net.runelite", name = "cache", version = rlver) {
        isTransitive = false
    }

    implementation(group = "com.google.code.gson", name = "gson", version = "2.8.5")
    implementation(group = "com.googlecode.json-simple", name = "json-simple", version = "1.1.1")
    implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.25")
//    implementation(group = "com.google.guava", name = "guava", version = "27.0.1-jre")
    implementation(group = "org.apache.ant", name = "ant", version = "1.9.4")

//    compileOnly(group = "org.projectlombok", name = "lombok", version = "1.18.4")

    // https://mvnrepository.com/artifact/org.apache.commons/commons-compress
    implementation("org.apache.commons:commons-compress:1.10")

// https://mvnrepository.com/artifact/net.java.dev.jna/jna
    implementation("net.java.dev.jna:jna:5.9.0")
}

//gradlePlugin {
//    plugins {
//        create("scriptAssemblerPlugin") {
//            id = "com.openosrs.scriptassembler"
//            implementationClass = "com.openosrs.scriptassembler.ScriptAssemblerPlugin"
//        }
//    }
//}

//val sourcesJar by tasks.registering(Jar::class) {
//    archiveClassifier.set("sources")
//    from(sourceSets.main.get().allSource)
//}
//
//publishing {
//    repositories {
//        maven {
//            url = uri("$buildDir/repo")
//        }
//        if (System.getenv("REPO_URL") != null) {
//            maven {
//                url = uri(System.getenv("REPO_URL"))
//                credentials {
//                    username = System.getenv("REPO_USERNAME")
//                    password = System.getenv("REPO_PASSWORD")
//                }
//            }
//        }
//    }
//    publications {
//        register("mavenJava", MavenPublication::class) {
//            from(components["java"])
//            artifact(sourcesJar.get())
//        }
//    }
//}