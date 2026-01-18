## Taint node 
```bash
kubectl get pod -o wide 

kubectl get nodes -o custom-columns=NAME:.metadata.name,TAINTS:.spec.taints
kubectl describe nodes | grep Taint


kubectl get node 

# to taint a node 
kubectl taint node node1 node-role.kubernetes.io/control-plane=:NoSchedule

# untaint a node 
kubectl taint node node1 node-role.kubernetes.io/control-plane-


# For tainting the worker node 
kubectl taint node node5 service=disabled:NoSchedule

# untaint the worker 
kubectl taint node node5 service=disabled:-NoSchedule


```

- Taint the worker5 to not accept tasks or running pod 


- NOde selector : 
Select a specific nodes to run the wokrloads 
```bash 
kubectl label nodes node-1 disktype=ssd
kubectl label nodes node-2 disktype=hdd

```
in your dployment 
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: example-pod
spec:
  nodeSelector:
    disktype: ssd
  containers:
  - name: nginx
    image: nginx
```


## Affinity vs Anti-affinity 

Affnity : add condition for your deployment 
ex. running this dployment in the same node as previous deployment. 

