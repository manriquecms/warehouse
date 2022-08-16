FROM openjdk:11-jre
MAINTAINER Cesar Manrique <manrique.cms@gmail.com>

ENTRYPOINT ["java", "-jar", "/usr/share/warehouse/warehouse.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
### ADD target/lib           /usr/share/myservice/lib
# Add the service itself
ARG JAR_FILE
ADD target/${JAR_FILE} /usr/share/warehouse/warehouse.jar
EXPOSE 8080