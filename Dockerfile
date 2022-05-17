FROM openjdk:11

COPY target/Easel_New-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch Easel_New-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","Easel_New-0.0.1-SNAPSHOT.jar"]