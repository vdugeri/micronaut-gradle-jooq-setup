FROM registry.access.redhat.com/ubi8/ubi-minimal:8.6
ARG JAVA_PACKAGE=java-17-openjdk-headless
WORKDIR /home/app

# Need to pass in git_token to pull from gopuff private repositories

RUN microdnf install git ${JAVA_PACKAGE} \
    && microdnf update \
    && microdnf clean all

COPY ./build/docker/main/layers/libs /home/app/libs
COPY ./build/docker/main/layers/classes /home/app/classes
COPY ./build/docker/main/layers/resources /home/app/resources
COPY ./build/docker/main/layers/application.jar /home/app/application.jar

ENV JAVA_OPTIONS="-Xshare:off -XX:InitialRAMPercentage=70 -XX:MinRAMPercentage=70 -XX:MaxRAMPercentage=70"

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/application.jar"]
