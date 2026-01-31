## NOTE 
> Note related to working with volumes 
in kubernetes 




```bash
kubectl get cm # configmap 
kubectl describe cm <name> 

```

## SECRET 
- You might need for the password , or some sensitive env 
Imperative Style 
Declarative Style (YAML File approach )
```bash 
kubectl create secret generic postgres-secret \
  --from-literal=POSTGRES_USER=admin-user \
  --from-literal=POSTGRES_PASSWORD=supersecret

```
```yaml 
env:
  - name: POSTGRES_PASSWORD
    valueFrom:
      secretKeyRef:
        name: postgres-secret
        key: POSTGRES_PASSWORD
```

- creating from the .env 

```bash 
# inside your .env 
POSTGRES_USER=postgres_user
POSTGRES_PASSWORD = postgresPassword 
```
you credential stores in env file 
```bash 
kubectl create secret generic \
    postgres-secret \
    --from-env-file=.env

```

## Waiting for working with nexus private docker images 
- `dockerconfigjson` , for getting the private image from the registry 
```bash 
kubectl create secret docker-registry regcred \
  --docker-server=https://index.docker.io/v1/ \
  --docker-username=myuser \
  --docker-password=mypassword \
  --docker-email=my@email.com

```