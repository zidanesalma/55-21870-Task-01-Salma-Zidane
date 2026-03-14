# 1. Base image
FROM eclipse-temurin:25.0.2_10-jdk

# 2. Set working directory
WORKDIR /app

# 3. Copy JAR into container
COPY target/salma_zidane-0.0.1-SNAPSHOT.jar app.jar

# 4. Copy JSON data files into /data
COPY src/main/resources/notes.json /data/notes.json
COPY src/main/resources/users.json /data/users.json

# 5. Set environment variables
ENV USER_NAME=Docker_Salma_Zidane
ENV ID=Docker_55_21870

# 6. Expose port
EXPOSE 8080

# 7. Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
