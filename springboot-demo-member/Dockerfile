FROM registry.redhat.io/ubi8/openjdk-8:1.14-3

ARG JAR_FILE=deployments/member-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /deployments/app.jar

USER root
RUN echo '172.16.46.100  gitlab.ocp4.local' >> /etc/hosts

#ENTRYPOINT ["java","-jar","/app.jar","--debug"]
