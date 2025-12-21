## NOTE about lesson 6 
CRONTAB, SERVICES, LOGROTATIONS 

```bash
tldr crontab
cat /etc/crontab

crontab -e 
# allow x for this file 
# chmod 777 ~/vagrant-script.sh
* * * * * /home/vagrant/vagrant-script.sh
crontab -l # list cron job 

# to view logs every 2 secs in realtime
watch cat ~/minites-log.log 

# in order to see the log from your cron services 
sudo systemctl status cron.service 
# or 
sudo service cron status 

# deleting the cronjob 
crontab -r # delete all cronjob of the current user. 
crontab -r -i # to prompt the Y/N before deleting
```


*** 
```bash
/var/log/custom/daily-log.log {
    daily
    rotate 7
    compress
    missingok
    notifempty
    create 0644 root root
    su root root
}


```


*** 
## Working with services 

```bash
sudo systemctl verb service 
sudo service  service verb 

sudo systemctl daemon-reload
sudo systemctl start docker 
sudo systemctl restart docker 
sudo systemctl disable docker 
sudo systemctl enable docker 
sudo systemctl stop docker 

sudo systemctl status docker 
sudo service docker status 

```