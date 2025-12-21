## NOTE for working with the docker networks 


There are different type of the network drivers: 
1. **bridge** (default) -> use docker0 -> host network interface 
2. **host** ( use host network interface )
3. **none** ( standalone )
4. **macvlan** 
5. **ipvlan** 
6. **overlay** ( docker swarm )


### Testing with bridge network
```bash
docker network ls 

docker network create -d bridge demo-network 
# run container inside the network 
# create interactive container , when stop it will autoremove the container 
docker run -it --rm --name container1 \
    --network demo-network \
    busybox:latest 

docker run -it --rm --name container2 \
    busybox:latest 

docker network connect demo-network container2
# test inside the container2 
nslookup container1
ping container1
```

### Testing with host network 
> allow you to use use the container with the existing host network interface , so you don't have to expose the ports. 
```bash

curl localhost:80  

docker run -dp 80:80 \
    --name nginx-cont nginx 

docker run -d --network host \
    --name nginx-cont nginx 

```