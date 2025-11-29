#!/bin/bash 

# create a simple function to use 
greeting(){
    local username="$1"
    echo " 👍 Welcome to the program " 
    echo "Usename is : ${username} " 
}

check_service(){
    local service_name="$1"
    if systemctl is-active --quiet "$service_name" ; then 
        echo " Service Name: $service_name is actived ✅"
    else
        echo "Service Name: $service_name is not actived ❌" 
    fi 
}

