## NOTE for the for the introduction to Linux 



```bash
mkdir folder
touch file 

# list folder or file 
ls 
ls -lrt 
ll 

# 
sudo apt install tree -y # -y confirm the installation prompt
sudo apt update && sudo apt upgrade -y 

tree folder 
tree  # print current directory 
tree -I folder-to-ignore 


# to read how to use command
man tree 
tree --help 

npm install -g tldr 
sudo apt install tldr -y  
tldr docker # No entry 
mkdir -p /home/vagrant/.local/share/tldr
tldr --update

```