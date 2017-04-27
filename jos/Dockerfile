FROM java:8
VOLUME /tmp

WORKDIR /opt/jos
ENV WORKDIR=/opt/jos

ADD jos.jar jos.jar
RUN bash -c 'touch $WORKDIR/jos.jar'

EXPOSE 8080

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar jos.jar
