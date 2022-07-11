FROM docker.io/wspark83/springboot:openjdk8

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /deployments/app.jar

#ENTRYPOINT ["java","-jar","/app.jar","--debug"]
