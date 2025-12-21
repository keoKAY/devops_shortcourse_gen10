## NOTE 
✅ What is the log rotation ? 
Log rotation is the process of automatically 
- Renaming old log files 
- Compressing them 
- Deleting very old ones 
- Keeping log size under control 

It prevents logs from consuming too much disk space. 
Linux uses a tool called `logrotate` to manage this. 

#### Where logrotate lives 
- main configurations 
`/etc/logrotate.conf `
- per-service configuration directory 
`/etc/logrotate.d/`

* if you want to check the timer 
```bash 
systemctl status logrotate.timer 
```
*** 
#### Breadown the logrotate directive 
1. `rotate` : How many old log files to keep 
2. `size`: Rotate when log reaches a specfiic szie (instead of time )
3. `daily/weekly/monthly`: This is the timebased rotations 
4. `compress` : compress old logs with gzip , disable the compressions , just use `nocompress`
5. `delaycompress`
- First rotation = not compressed yet 
- Next rotation = previoius log compress 
> Useful for nginx / apache so that current log stays readable
6. `missingok`
Do not error if log doesn't exists 
7. `notifempty`
Do not rotate if eempty logs 
8. `create`
Create a new empty log file after rotation with specific permission 
`create 0644 root root`
9. `postrotate/endscript`
Run a command after rotation 
Ex. Reload nginx so it reopen the logs files 
```bash 
postrotate
    systemctl reload nginx 
endscript
```
- Create  a custom logrotate configuration 
```bash 
sudo nano /etc/logrotate.d/myapp
```
```bash
/var/log/myapp/*.log {
    daily
    rotate 7
    size 100M
    compress
    delaycompress
    missingok
    notifempty
    create 0640 root adm
    postrotate
        systemctl restart myapp
    endscript
}
```

if you want to manually run the rogrotates 