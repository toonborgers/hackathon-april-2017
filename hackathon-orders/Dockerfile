FROM java:8-alpine
VOLUME /tmp

WORKDIR /opt/orders
ENV WORKDIR=/opt/orders

ADD orders.jar orders.jar
RUN sh -c 'touch $WORKDIR/orders.jar'

EXPOSE 8080

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar orders.jar
