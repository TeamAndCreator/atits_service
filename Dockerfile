FROM  tomcat
MAINTAINER liulianjushi@126.com
ENV CATALINA_HOME /usr/local/tomcat
RUN mkdir /root/File
ADD ./target/atits_service.war $CATALINA_HOME/webapps/
ADD ./server.xml $CATALINA_HOME/conf/server.xml
WORKDIR $CATALINA_HOME
CMD ["catalina.sh", "run"]