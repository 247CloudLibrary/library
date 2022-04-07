# Dockerfile 생성
FROM amazoncorretto:17
ARG JAR_FILE=build/libs/library-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} library.jar
ENV JAVA_OPTS=""
CMD java $JAVA_OPTS -server -jar library.jar