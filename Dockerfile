FROM tomcat:latest
ADD target/sgved.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]