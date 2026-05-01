plugins {
    id("io.freefair.lombok")
    id("uk.co.jasonmarston.project.standards.quarkus-library")
}

description = "Validation utilities and constraint handling helpers."

dependencies {
    implementation(project(":utility-domain-exception"))
    implementation("io.quarkus:quarkus-jackson")
    implementation("io.quarkus:quarkus-hibernate-validator")
}
