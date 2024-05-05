# Use an official Maven image as the base image
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM maven:3.9.6-eclipse-temurin-21
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/unit-conversion-0.0.1-SNAPSHOT.jar ./unitconversion.jar
EXPOSE 8080
# Set the command to run the application
CMD ["java", "-jar", "unitconversion.jar"]

# # Stage 1: Install Maven
# FROM eclipse-temurin:21-jdk-alpine AS maven

# RUN apk add --no-cache curl tar bash
# ENV MAVEN_HOME /usr/share/maven
# COPY --from=apache/maven:3.8.4-adoptopenjdk-11 /opt/maven ${MAVEN_HOME}
# RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn

# # Stage 2: Build the application
# FROM maven AS build
# WORKDIR /app
# COPY pom.xml .
# COPY src ./src
# RUN mvn clean install

# # Stage 3: Run the application
# FROM eclipse-temurin:21-jdk-alpine
# WORKDIR /app
# COPY --from=build /app/target/unit-conversion-0.0.1-SNAPSHOT.jar ./unit-conversion.jar
# EXPOSE 8080
# CMD ["java", "-jar", "unit-conversion.jar"]
