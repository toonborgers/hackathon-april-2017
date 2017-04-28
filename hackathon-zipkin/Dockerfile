FROM java:8-alpine
VOLUME /tmp

WORKDIR /opt/zipkin
ENV WORKDIR=/opt/zipkin

ADD zipkin.jar zipkin.jar
RUN sh -c 'touch $WORKDIR/zipkin.jar'

EXPOSE 8083

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar zipkin.jar
