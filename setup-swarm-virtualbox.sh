#!/usr/bin/env bash
# Define the number of managers/workers
MANAGER=3
WORKER=5

# Create the Docker hosts
for i in $(seq 1 $MANAGER); do docker-machine create --driver virtualbox manager$i; done
for i in $(seq 1 $WORKER); do docker-machine create --driver virtualbox worker$i; done

MANAGER1_IP=$(docker-machine ip manager1)

# Init the swarm
docker-machine ssh manager1 docker swarm init --listen-addr $MANAGER1_IP:2377 --advertise-addr=$MANAGER1_IP:2377

WORKER_TOKEN=$(docker-machine ssh manager1 docker swarm join-token worker -q)
MANAGER_TOKEN=$(docker-machine ssh manager1 docker swarm join-token manager -q)


# Add additional manager(s)
for i in $(seq 2 $MANAGER); do docker-machine ssh manager$i docker swarm join --token $MANAGER_TOKEN  $MANAGER1_IP:2377; done

# Add workers
for i in $(seq 1 $WORKER); do docker-machine ssh worker$i docker swarm join --token $WORKER_TOKEN $MANAGER1_IP:2377; done
