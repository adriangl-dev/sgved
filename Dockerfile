FROM openjdk:12-alpine
RUN mkdir -p /usr/db
ADD target/sgved.jar /usr/share/sgved.jar
ADD ./src/main/resources/data/bd_sgved* /usr/db
ENTRYPOINT ["java", "-jar", "/usr/share/sgved.jar", "--DB_PATH=/usr/db/bd_sgved"]