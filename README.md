# Preparations

1. Install docker machine: https://docs.docker.com/machine/
2. Add the play with docker driver (optional): https://github.com/franela/docker-machine-driver-pwd 


# Docker swarm setup blog

http://lucjuggery.com/blog/?p=566

# Play with docker

https://github.com/franela/docker-machine-driver-pwd
http://play-with-docker.com

Set docker machine: eval $(docker-machine env <machine>)
Unset docker machine: eval $(docker-machine env -u)

# UCP

`docker run --rm -it --name ucp -v /var/run/docker.sock:/var/run/docker.sock docker/ucp install
 --force-insecure-tcp --san *.play-with-docker.com --host-address $(hostname -i) --admin-usernam
e toon --interactive`
