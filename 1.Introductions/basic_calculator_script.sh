#!/bin/bash
read -p "Enter Num1: " num1 
read -p "Enter Num2: " num2 
read -p "Coose (+,-,*,\): " option 

# switch case 
case $option in 
    +)
    echo "Adding the numbers"
    #result=$(echo "$num1 + $num2"| bc)
    result=$(($a + $b))
    echo "Result is : $result"
    ;; 
    -)
    echo "Subtracting the numbers"
    result=$(echo "$num1 - $num2"| bc)
    echo "Result is : $result"
    ;; 
    \*)
    echo "Multipling the numbers"
    result=$(echo "$num1 * $num2"| bc)
    echo "Result is : $result"
    ;; 

    /)
    echo "Dividing the numbers"
    # add the condition if the num2 is zeo 
    result=$(echo "$num1 / $num2"| bc)
    echo "Result is : $result"
    ;; 

    *)
    echo " ❌ Invalid Option!! Please choose (+,-,*,/)"
    ;; 
echo "Thank you for using the script 👍"