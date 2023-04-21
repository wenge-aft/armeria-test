import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.*

plugins {
    kotlin("jvm") version "1.7.20"
    application
    id("com.google.protobuf") version "0.8.19"
}

group = "com.afterpay.martech.script"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



dependencies {

    implementation("com.google.protobuf:protobuf-java:3.6.1")
    implementation("io.grpc:grpc-stub:1.15.1")
    implementation("io.grpc:grpc-protobuf:1.15.1")
    protobuf(files("proto/"))
    // Adjust the list as you need.
    listOf(
      "armeria",
      "armeria-brave",
      "armeria-grpc",
      "armeria-jetty9",
      "armeria-kafka",
      "armeria-logback",
      "armeria-retrofit2",
      "armeria-rxjava3",
      "armeria-saml",
      "armeria-thrift0.13",
      "armeria-tomcat9",
      "armeria-zookeeper3",
      "armeria-protobuf"
    ).forEach {
        implementation("com.linecorp.armeria:${it}:1.15.0")
    }
    if (JavaVersion.current().isJava9Compatible()) {
        // Workaround for @javax.annotation.Generated
        // see: https://github.com/grpc/grpc-java/issues/3633
        implementation("javax.annotation:javax.annotation-api:1.3.1")
    }
    testImplementation(kotlin("test"))


}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}


protobuf {
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.6.1:osx-x86_64"
    }
    plugins {
        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.15.1:osx-x86_64"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without
                // options. Note the braces cannot be omitted, otherwise the
                // plugin will not be added. This is because of the implicit way
                // NamedDomainObjectContainer binds the methods.
                id("grpc") { }
            }
        }
    }
}

sourceSets {
    main {
        java.srcDir("build/generated/source/proto/main")
    }
}