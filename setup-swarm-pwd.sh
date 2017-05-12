
# Define the number of managers/workers
MANAGER=2
WORKER=2

URL=$1

# Check param
if [[ $# -eq 0 ]] ; then
    echo 'Play-with-docker url required!'
    open http://www.play-with-docker.com
    exit 1
fi

# Check driver
hash docker-machine-driver-pwd &> /dev/null
if [ $? -eq 1 ]; then
    echo 'Play-with-docker driver required!'
    open https://github.com/franela/docker-machine-driver-pwd
    exit 1
fi

# Create the Docker hosts
echo "Creating managers"
for i in $(seq 1 $MANAGER); do docker-machine create -d pwd --pwd-url $URL manager$i; done
echo "Creating workers"
for i in $(seq 1 $WORKER); do docker-machine create -d pwd --pwd-url $URL worker$i; done
echo "Machines created:"
docker-machine ls

# Set up connection to first manager
MANAGER1_IP=$(docker-machine ip manager1)
eval $(docker-machine env manager1)

# Create swarm + get tokens
echo "Creating swarm"
docker swarm init --advertise-addr eth0

MANAGER_TOKEN=$(docker swarm join-token manager -q)
echo "Manager token:"
echo $MANAGER_TOKEN
WORKER_TOKEN=$(docker swarm join-token worker -q)
echo "Worker token:"
echo $WORKER_TOKEN

# Join other managers
echo "Joining managers"
for i in $(seq 2 $MANAGER); do eval $(docker-machine env manager$i); docker swarm join --token $MANAGER_TOKEN $MANAGER1_IP:2377; done

# Join workers
echo "Joining workers"
for i in $(seq 1 $WORKER); do eval $(docker-machine env worker$i); docker swarm join --token $WORKER_TOKEN $MANAGER1_IP:2377; done

echo "Set up cluster:"
eval $(docker-machine env manager1)
docker node ls
