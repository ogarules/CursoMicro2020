
FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine
#FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/employee-0.0.1-SNAPSHOT.jar employee.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar employee.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar employee.jar
