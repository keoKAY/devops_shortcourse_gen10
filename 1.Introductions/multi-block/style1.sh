#!/bin/bash

echo "Writing the configuration as text block 👍"
cat << EOF > sample-config.conf

This is the configuration 
of nginx service 
that will be server 

server {
    listen 80;
    listen [::]:80;
    server_name example.com www.example.com;
    return 301 https://\$host\$request_uri;
}

EOF



