# Use an OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file (assuming you have built your jar)
COPY target/leukedingetjes-0.0.1-SNAPSHOT.jar /app/leukedingetjes-0.0.1-SNAPSHOT.jar

# Expose the port your application is running on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "your-application.jar"]
