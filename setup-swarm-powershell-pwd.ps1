param([string]$url)

$managers=2
$workers=2

if( $url.Length -eq 0){
 echo 'Play-with-docker url required!'
 exit 1
}

# Create the Docker hosts
echo "Creating managers"

For ($i=1; $i -le $managers; $i++) {
  docker-machine create -d pwd --pwd-url $URL manager$i    
}

echo "Creating workers"

For ($i=1; $i -le $managers; $i++) {
  docker-machine create -d pwd --pwd-url $URL worker$i    
}

echo "Machines created:"
docker-machine ls

$manager1_ip=(docker-machine ip manager1)
docker-machine env manager1 | Invoke-Expression

# Create swarm + get tokens
echo "Creating swarm"
docker swarm init --advertise-addr eth0

$managerToken=(docker swarm join-token manager -q)
$workerToken=(docker swarm join-token worker -q)

echo "Manager token:"
echo $managerToken
echo "Worker token:"
echo $workerToken

# Join other managers
echo "Joining managers"

For($i=2;$i -le $managers;$i++){
  docker-machine env manager$i | Invoke-Expression
  docker swarm join --token $managerToken $manager1_ip':2377'
}

# Join workers
echo "Joining workers"

For($i=1;$i -le $workers;$i++){
  docker-machine env worker$i | Invoke-Expression
  docker swarm join --token $workerToken $manager1_ip':2377'
}

echo "Set up cluster:"
docker-machine env manager1 | Invoke-Expression
docker node ls
