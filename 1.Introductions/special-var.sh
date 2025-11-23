#!/bin/bash

username="$1" 
age="$2" 
# ${10} 10th parameters 
# if else : ensure that the values will be provided
if [ -z "$username" ]; then 
    echo "Usage: bash $0 <username>"
    echo "Username must be provided ❌" 
    exit 1 
fi 

echo "File name is : $0"
echo "Username is : $username"
echo "Age is : $age"
echo "Number of param: $#"
echo "Status Code : $?"
echo "Values of the parameters: $*"