plugins {
    id("uk.co.jasonmarston.project.standards.quarkus-library")
}

dependencies {
    implementation(project(":utility-validator"))
    implementation("org.modelmapper:modelmapper")
    implementation("org.modelmapper:modelmapper-module-record")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-hibernate-validator")
}


