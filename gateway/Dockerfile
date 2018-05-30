FROM java:8-jre
MAINTAINER NIJO GEORGE P <nijomon@gmail.com>

ADD ./target/ugateway-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ugateway-0.0.1-SNAPSHOT.jar"]

EXPOSE 4000