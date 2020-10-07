FROM adoptopenjdk/openjdk11:jdk-11.0.6_10-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/organization-0.0.1-SNAPSHOT.jar organization.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar organization.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar organization.jar
