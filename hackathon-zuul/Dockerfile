FROM java:8-alpine
VOLUME /tmp

WORKDIR /opt/zuul
ENV WORKDIR=/opt/zuul

ADD zuul.jar zuul.jar
RUN sh -c 'touch $WORKDIR/zuul.jar'

EXPOSE 8081

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar zuul.jar
