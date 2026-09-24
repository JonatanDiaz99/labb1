FROM tomcat:9.0-jdk21

COPY target/labb1.war /usr/local/tomcat/webapps/labb1.war

EXPOSE 8080