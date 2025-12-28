## NOTE 
Working with ansible 

```bash

192.168.56.10
192.168.56.11 
192.168.56.19

cd 
cd .ssh
# find file called id_rsa.pub 
ssh-keygen 
ssh-keygen -R 192.168.56.10
ssh vagrant@192.168.56.10
ssh vagrant@192.168.56.11

```

## Ansible `adhoc` command 

- Use this commands to ping to see if the inventory valid or not 
```bash
ansible -i inventory.ini node1 -m ping 
ansible -i inventory.ini masters -m ping 
ansible -i inventory.ini workers -m ping 


ansible -i inventory.ini workers -m command -a "uptime" 

# install nginx for all machine in workers group 
# apt update && apt upgrade -y 
ansible workers -i inventory.ini -m apt -a "update_cache=yes" -b
# apt install nginx 
ansible -i inventory.ini workers \
    -m apt -a "name=nginx state=present" -b

ansible -i inventory.ini workers \
    -m apt -a "name=neofetch state=present" -b

# uninstall the service 
ansible -i inventory.ini workers \
    -m apt -a "name=neofetch state=absent" -b
```

## Ansible playbook

```bash
ansible-playbook -i inventory.ini playbooks/install-nginx.yaml 

ansible-playbook -i inventory.ini playbooks/deploy-svc.yaml 

ansible-galaxy collection install community.general
ansible-galaxy collection uninstall community.general

```