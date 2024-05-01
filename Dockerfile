FROM gradle:8.7-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test
EXPOSE 8000
SHELL ["/bin/bash", "-c"]
ENTRYPOINT ["java", "-jar", "./build/libs/MeetMephi2-0.0.1-SNAPSHOT.jar"]