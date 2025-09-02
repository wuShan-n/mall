FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/mall-gateway-1.0.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS="-Xms512m -Xmx1024m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]