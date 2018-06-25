FROM java:8
VOLUME /tmp
ADD spring-boot-onlinebanking-0.1.0.jar onlinebanking.jar
RUN bash -c 'touch /onlinebanking.jar'
ENTRYPOINT ["java","-D<java.security.egd=file:/dev/./urandom","-jar","/onlinebanking.jar"]