plugins {
    id("uk.co.jasonmarston.project.standards.quarkus-library")
}

// Used in github pages
description = "Exception-to-response mapping utilities for REST services."

dependencies {
    implementation(project(":utility-domain-exception"))
    implementation(project(":utility-validator"))
    implementation("io.quarkus:quarkus-rest-jackson-common")
    implementation("io.quarkus:quarkus-hibernate-validator")
    implementation("org.modelmapper:modelmapper")
}
