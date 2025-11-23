#!/bin/bash 

# check if the command exists before running subsequence commands. 

# nginx , docker 
echo -e "\tTabbed output \n Hello world " 
if docker -v &>/dev/null; 
then 
    echo "Docker version is : $(docker -v)"
fi 

nginx -v &>/dev/null 
if [ "$?" -eq 0 ]; then 
    echo "Nginx is already installed " 
    # sudo apt remove nginx -y
    # sudo apt autoclean -y 
else 
    echo -e "Nginx not found! ❌ \nInstall nginx on the system"
fi 

nginx -v &>/dev/null || {
    echo "Nignx not found ❌"
    echo "Let's install latest version of nginx " 
    sudo apt install nginx -y 
}