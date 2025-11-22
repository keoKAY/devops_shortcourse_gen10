

```bash
useradd  # normal command 
adduser  # add user utility 

sudo useradd james 
sudo adduser koko

# create user with default shells , create home dir 
sudo useradd superman \
    --create-home \
    --shell /usr/bin/zsh

# to login to different user account 
su username 
su - username 

# to change your password
passwd 
# to change password for other user 
sudo passwd superman



############
# USER MOD #
############
sudo usermod -aG group username 
sudo usermod -aG docker $USER 
sudo newgrp docker 

docker ps
docker run hello-world 

sudo usermod -aG sudo $USER 


#####################
#  Delete User      #
#####################

sudo userdel 
sudo deluser 

sudo deluser --remove-home koko 
sudo deluser --remove-home superman 
# delete all files that belongs to superman 
sudo deluser --remove-all-files superman

# delete but keep it in /etc/backup 
sudo deluser \
    --backup-to /var/backups \
    --remove-home superman
```

### Working with group 
```bash

cat /etc/group
cat /etc/group | grep "name"
cat /etc/group | tail -n 5 # read from below , 5 line 
cat /etc/group | head -n 5 # read from above , 5 line 

groups # check which group we are in 
groupadd 
groupdel 



```
### Permission and ownership 
```bash
ls -a 
ll 
ls -lrt 

# to create a symbolic link 
ln -s target-to-link  folder-to-use-file 
ln -s ~/basic-server/link-test.cpp ~/test-folder

# using absolute mode 
4=read
2=write
1=run(execute)
# 0-7 
chmod 777 file or folder 
chmod -R 777 folder/*

# running the script to test 
./demo.sh
echo "Hello world from demo.sh" 

bash demo.sh # no need add 'x' for permission 


#  Change the ownership of files or folders 
sudo chown user:group file or folder 
sudo chown $USER:$USER  file or folder 
sudo chown -R $USER:$USER  folder 



# working with symbolic 
# + add addition permission 
# - remove 
# = override 

# rw +x = rwx 
# rw -w = r-- 
# r =rwx = rwx 

# to change group of folder or file 
sudo chgrp group folder or file 
```