## NOTE for working with nexus oss 

1. Create GCP instance 
- Install portainer for eaily monitor and see all containers 

2. Deploy Nexusoss 
3. Configure nginx 
4. Configure namespace for adding domain for nexus

```bash
public-ip: 34.x.x.x
neuxs.anajak-khmer.site -> for UI : 8081
nexus-cr.anajak-khmer.site -> docker registry :5000
```

- configure nginx to point to service with domain name

`/etc/nginx/conf.d/`: create file nexus.conf 
```conf
server {
   listen 80;
   listen [::]:80;
   server_name nexus.anajak-khmer.site;


   location / {

       proxy_pass http://localhost:8081;
       proxy_set_header Host $http_host;
       proxy_set_header X-Real-IP $remote_addr;
       proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
       proxy_set_header X-Forwarded-Proto $scheme;

   }

}

```
`/etc/nginx/conf.d/`: create file nexus-cr.conf 
```conf
server {
   listen 80;
   listen [::]:80;
   server_name nexus-cr.anajak-khmer.site;


   location / {
      client_max_body_size 10G;
       proxy_pass http://localhost:5000;
       proxy_set_header Host $http_host;
       proxy_set_header X-Real-IP $remote_addr;
       proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
       proxy_set_header X-Forwarded-Proto $scheme;

   }
}

```

> **Note**: you can also put the config file in `site-available `and link it to `site-enable` to achieve the same thing as well,. 

- Request https for nexus and nexus-cr
```bash 
sudo apt install certbot -y
sudo apt install python3-certbot-nginx -y


# to add https for your services 
sudo certbot --nginx 
```
5. Configure certbot


***
### Setup docker registry inside nexus oss 

docker register 
1. blob store -> folder store the images 
ex. docker-blob 
    helm-blob 


```bash 
docker login -u admin nexus-cr.anajak-khmer.site 
# enter your password 

docker tags nginx:latest \
    nexus-cr.anajak-khmer.site/demo-nginx:v1.0.0 

docker push nexus-cr.anajak-khmer.site/demo-nginx:v1.0.0
```


### COnfigure user and privileges 
- Privilege for **readonly** in docker-hosted repo 
```bash
nx-repository-view-docker-docker-hosted-browse
nx-repository-view-docker-docker-hosted-read
```
- Priviledge for **fullaccess** with the dockerhosted repo 
```bash
nx-repository-view-docker-docker-group-browse
nx-repository-view-docker-docker-group-read
nx-repository-view-docker-docker-group-add
nx-repository-view-docker-docker-group-edit
nx-repository-view-docker-docker-group-delete

```