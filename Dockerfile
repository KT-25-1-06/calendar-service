# Stage 1: Build the application
FROM gradle:8.5-jdk17 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and configuration files
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Cache dependencies by running the dependencies task
RUN ./gradlew dependencies --no-daemon

# Copy the rest of the source code
COPY src src

# Build the application
RUN ./gradlew bootJar --no-daemon

# Stage 2: Create the runtime image
FROM eclipse-temurin:17-jre-jammy AS runtime

# Set the working directory inside the container
WORKDIR /app

# Copy only the built JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 80

# Set the environment variable for Spring profile
ENV SPRING_PROFILES_ACTIVE=prod

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 