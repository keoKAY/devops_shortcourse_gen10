## NOTE 


namespace : 
team-A, team-B
```bash 
kubectl get pod -n default 
kubectl get pod -A
```

## Initial dashboard setup 
- Expose Dashboard Service from ClusterIP to NodePort 
```bash 
kubectl edit svc kubernete-dashboard -n kube-system 

# Create firewall rule for port 30000 - 32767
https://34.87.102.228:32048

```
- create serviceaccount 
- create clusterrolebinding 
- Generating the token for dashboard 

## Initial ingress setup 
## Initial Argocd setup 