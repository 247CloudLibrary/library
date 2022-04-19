FROM 844148244640.dkr.ecr.us-east-1.amazonaws.com/adoptopenjdk:latest AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM 844148244640.dkr.ecr.us-east-1.amazonaws.com/adoptopenjdk:latest
COPY --from=builder build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]