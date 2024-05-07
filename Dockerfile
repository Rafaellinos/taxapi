# Use the official Maven image as the base image
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package

# Download open telemetry jar
RUN curl -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar > /app/opentelemetry-javaagent.jar


# Use the official OpenJDK 17 image as the base image for the runtime environment
FROM openjdk:17-slim

# Set the working directory in the container
WORKDIR /app

# Configure OpenTelemetry with environment variables
# PS: otlp is the protocol
ENV OTEL_SERVICE_NAME tax-api
ENV OTEL_EXPORTER_OTLP_ENDPOINT http://localhost:4317
ENV OTEL_EXPORTER_OTLP_PROTOCOL grpc
ENV OTEL_EXPORTER_OTLP_INSECURE true
ENV OTEL_TRACES_EXPORTER otlp
ENV OTEL_METRICS_EXPORTER otlp
ENV OTEL_LOGS_EXPORTER otlp

# Copy the built artifact from the previous stage
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

# Expose port 8081
EXPOSE 8081

# Run the Spring Boot application with Open Telemetry
CMD ["java", "-javaagent:/app/opentelemetry-javaagent.jar", "-jar", "app.jar"]
