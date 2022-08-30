FROM registry.redhat.io/ubi8/openjdk-8:1.14-3
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} /deployments/app.jar

#ENTRYPOINT ["java","-jar","/app.jar","--debug"]
