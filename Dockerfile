# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim
# Set the working directory in the container
WORKDIR /app

# Copy all necessary files
COPY gradlew gradlew
COPY gradlew.bat gradlew.bat
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./

# Set executable permissions for gradlew
RUN chmod +x gradlew

# Install required dependencies
RUN apt-get update  \
    && apt-get install -y bash dos2unix  \
    && dos2unix gradlew

# Test gradlew
RUN bash ./gradlew --version

# Build the application with gradlew
RUN bash ./gradlew clean bootJar

# Copy the built jar file to the container
COPY build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

# Build: docker build -t ducvui2003/spring-delivery  .
# Tag: docker tag ducvui2003/spring-delivery ducvui2003/spring-delivery:version
# Push: docker push ducvui2003/spring-delivery:version