# we will use openjdk 8 with alpine as it is a very small linux distro
FROM amazoncorretto:17-alpine-jdk

# copy the packaged jar file into our docker image
COPY target/diploma-0.0.1-SNAPSHOT.jar /diploma.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/diploma.jar"]