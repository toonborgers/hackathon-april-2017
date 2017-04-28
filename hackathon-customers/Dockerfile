FROM java:8-alpine
VOLUME /tmp

WORKDIR /opt/customers
ENV WORKDIR=/opt/customers

ADD customers.jar customers.jar
RUN sh -c 'touch $WORKDIR/customers.jar'

EXPOSE 8082

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar customers.jar
