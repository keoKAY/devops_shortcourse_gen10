## Note 

1. Add repo of the monitoring helm chart 
```bash 
helm repo add prometheus-community \
    https://prometheus-community.github.io/helm-charts
helm repo update
# how do we install this in the specific namespace 
helm install monitor-stack-release \
    prometheus-community/kube-prometheus-stack

# To esure that everything is running 
kubectl --namespace default \
    get pods \
    -l "release=monitor-stack-release"

kubectl get secret -n default monitor-stack-release-grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo

kubectl get secret -n default monitor-stack-release-grafana -o jsonpath="{.data.admin-password}" | base64 --decode 
```

### Configure the domain name for the prometheus and grafana 
- We only need to configure the domain name for the grafana as the visualizer for seeing things
> You can find the ingress configurations 

### Configute notification channel for the alert to fired 
- We will use the telegram as our notification channel 

```bash
# 1. Run commmand i order to get the orignal value 
helm show values prometheus-community/kube-prometheus-stack > values.yaml
# 2. After we update the value file , we can upgrade helm chart 
helm upgrade \
    monitor-stack-release \
    prometheus-community/kube-prometheus-stack \
    -f values.yaml \
    -n default --install


# forward-port in order to access the services 
kubectl port-forward \
    svc/monitor-stack-release-kube-prometheus 9090:9090
```


More topics to research

- Role-based access control 
- Setup HA from scratch , load balancer ( HAProxy + Keepalived , MetalLB )
- Kubernetes for Admins
- Install Rancher 
- Kubenetes with Services Mesh (Istio)
- Kuebrnetes with Microserives 
