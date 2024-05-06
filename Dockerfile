
# Use an official Maven image as the base image for the build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Set the working directory in the container
WORKDIR /app
# Copy only the pom.xml to the container to cache Maven dependencies separately
COPY pom.xml .
# Download dependencies defined in pom.xml but do not build the project yet
RUN mvn dependency:go-offline
# Copy the project files to the container
COPY src ./src
# Build the application using Maven
RUN mvn package -DskipTests

# Use a lightweight OpenJDK image as the base image for the final stage
FROM maven:3.9.6-eclipse-temurin-21
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/unit-conversion-0.0.1-SNAPSHOT.jar ./unitconversion.jar
# Expose the port the application runs on
EXPOSE 8080
# Add a health check to verify the application is running
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1
# Set the command to run the application
CMD ["java", "-jar", "unitconversion.jar"]



# # Use an official Maven image as the base image
# FROM maven:3.9.6-eclipse-temurin-21 AS build
# # Set the working directory in the container
# WORKDIR /app
# # Copy the pom.xml and the project files to the container
# COPY pom.xml .
# COPY src ./src
# # Build the application using Maven
# RUN mvn clean package -DskipTests
# # Use an official OpenJDK image as the base image
# FROM maven:3.9.6-eclipse-temurin-21
# # Set the working directory in the container
# WORKDIR /app
# # Copy the built JAR file from the previous stage to the container
# COPY --from=build /app/target/unit-conversion-0.0.1-SNAPSHOT.jar ./unitconversion.jar
# # Expose the port the application runs on
# EXPOSE 8080
# # Set the command to run the application
# CMD ["java", "-jar", "unitconversion.jar"]
