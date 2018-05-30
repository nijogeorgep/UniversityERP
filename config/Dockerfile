FROM java:8-jre
MAINTAINER NIJO GEORGE P <nijomon@gmail.com>

ADD ./target/uconfig-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/uconfig-0.0.1-SNAPSHOT.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8888/health || exit 1

EXPOSE 8888