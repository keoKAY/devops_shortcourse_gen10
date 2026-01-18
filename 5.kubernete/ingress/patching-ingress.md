## Update ingress for easily approach 

```bash 
kubectl get svc -n kube-system
https://34.87.102.228:32048


#--publish-service
--publish-status-address=10.148.0.2,10.170.0.4,10.170.0.5,10.148.0.7,10.148.0.8
```

- Original config of ingress controller 
```
          args:
            - /nginx-ingress-controller
            - '--configmap=$(POD_NAMESPACE)/ingress-nginx'
            - '--election-id=ingress-controller-leader-nginx'
            - '--tcp-services-configmap=$(POD_NAMESPACE)/tcp-services'
            - '--udp-services-configmap=$(POD_NAMESPACE)/udp-services'
            - '--annotations-prefix=nginx.ingress.kubernetes.io'
            - '--ingress-class=nginx'
            - '--watch-ingress-without-class=true'
            - '--publish-service=ingress-nginx/ingress-nginx'
```