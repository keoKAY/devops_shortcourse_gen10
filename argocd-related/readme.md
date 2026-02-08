## 1. Working with webhook 
```bash 

# configure the github secret for secured communication 
kubectl get secret -n argocd 
kubectl edit secret argocd-secret -n argocd 

echo -n "securedpass123" | base64
webhook.github.secret: <your-base64-token> 
```

