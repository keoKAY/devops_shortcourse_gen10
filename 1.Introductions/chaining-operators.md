## CHAINING OPERATORS 

```bash 
which docker; date 
which docker 
# 2nd command will works if 1st one work
which docker &>/dev/null && docker -v 

# 2nd command will not run 
# dockers is not a valid command
which dockers &>/dev/null && docker -v 

# 2nd command work if the first one fail 
which dockers &>/dev/null || docker -v 

# we can also use this to hide the output when update apt 
sudo apt update &>/dev/null
```