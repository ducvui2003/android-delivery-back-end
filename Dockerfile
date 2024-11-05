# Stage 1: Build Stage
FROM gradle:8.2.1-jdk21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper, build.gradle.kts, and settings.gradle.kts for dependency caching
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Download dependencies to cache them if only build files change
RUN gradle build -x test --no-daemon || return 0

# Copy the source code to the working directory
COPY src ./src

# Build the application, generating a JAR file
RUN gradle bootJar -x test --no-daemon

# Stage 2: Run Stage
FROM eclipse-temurin:21-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port that Spring Boot will run on
EXPOSE 8080

# Optional: Set environment variables for Java options
ENV JAVA_OPTS=""

# Run the Spring Boot application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]