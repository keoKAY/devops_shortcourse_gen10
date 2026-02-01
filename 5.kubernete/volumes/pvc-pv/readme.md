## NOTE 


Using helm to install NFS external provision 

```bash 

# add repository , to find the chart that we will need to install 
helm repo add nfs-subdir-external-provisioner https://kubernetes-sigs.github.io/nfs-subdir-external-provisioner/

helm install nfs-subdir-external-provisioner nfs-subdir-external-provisioner/nfs-subdir-external-provisioner \
    --set nfs.server=10.148.0.2 \
    --set nfs.path=/srv/nfs_shared

```