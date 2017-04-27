FROM java:8-alpine
VOLUME /tmp

WORKDIR /opt/eureka
ENV WORKDIR=/opt/eureka

ADD eureka.jar eureka.jar
RUN sh -c 'touch $WORKDIR/eureka.jar'

EXPOSE 8791

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar eureka.jar
