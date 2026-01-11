

```bash 
pip install -r requirement.txt 
ansible-playbook  -b -v -i inventory/sample/inventory.ini cluster.yml
ansible-playbook -b -v -i inventory/sample/inventory.ini reset.yml



sudo kubectl get pod -A 
u



sudo kubectl get pod -A 

mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown -R $USER:$USER ~/.kube
sudo chmod 600 ~/.kube/config

````