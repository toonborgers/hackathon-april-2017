FROM java:8-alpine
VOLUME /tmp

WORKDIR /opt/jos
ENV WORKDIR=/opt/jos

ADD jos.jar jos.jar
RUN sh -c 'touch $WORKDIR/jos.jar'

EXPOSE 8080

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar jos.jar
