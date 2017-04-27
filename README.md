# Preparations

1. Install docker machine: https://docs.docker.com/machine/
2. Add the play with docker driver: https://github.com/franela/docker-machine-driver-pwd 


# Set up cluster

1. Go to [Play with docker](http://play-with-docker.com)
2. Run the script: `setup-swarm-pwd.sh URL` where URL is the full play-with-docker url

# Deploying the applications

## Networks

### eureka 

Used for service discovery: `docker network create -d overlay eureka`

## Services

Make sure that the docker images are deployed to docker hub!

### Eureka

Netflix service discovery tool. Directory: hackathon-eureka

`docker service create --name eureka --replicas 1 --network=eureka -p 8791:8791 toonborgers/hackathon-eureka:<VERSION>`

### Zuul

Netflix edge service. Directory: hackathon-zuul

`docker service create --name zuul --replicas 1 --network=eureka -p 8081:8081 -e "SPRING_PROFILES_ACTIVE=swarm" toonborgers/hackathon-zuul:<VERSION>`

### Orders

Simple Spring Boot application

`docker service create --name orders --replicas 1 --network=eureka -e "SPRING_PROFILES_ACTIVE=swarm" toonborgers/hackathon-orders:<VERSION>`

# Helpful commands

* `eval $(docker-machine env manager1)` connects the local docker client to the cluster manager's docker service, this means all docker commands you execute run on the remote instance
* `eval $(docker-machine env -u)` connects the local docker client back to the local docker

# UCP (management console) -> Optional

`docker run --rm -it --name ucp -v /var/run/docker.sock:/var/run/docker.sock docker/ucp install --force-insecure-tcp --san *.play-with-docker.com --host-address $(hostname -i) --admin-username toon --interactive`
