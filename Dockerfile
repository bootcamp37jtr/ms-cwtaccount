FROM openjdk:11
VOLUME /tmp
EXPOSE 9048
ADD ./target/ms-cwtaccount-0.0.1-SNAPSHOT.jar ms-cwtaccount.jar
ENTRYPOINT ["java","-jar","/ms-cwtaccount.jar"]