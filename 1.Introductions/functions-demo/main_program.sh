#!/bin/bash

util_file="./utilities.sh" 
# only source the file if the file exists 
if [ -f $util_file ]; then 
    echo "Applying the function definition...!" 
    source $util_file
else 
    echo "File = $util_file doesn't exist ❌" 
    exit 1 
fi 

echo "Filename is : $0" 
greeting "james" 
greeting "bona"

check_service "nginx" 