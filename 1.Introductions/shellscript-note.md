
## CUT & TR 
to cut out some part from the text 
`tr` : used to replace and delete texts , normally used with cut 
```bash 
cut -d " " -f 3 cut-sample.txt

# cut to extract email with @ 
# tr to replace @ to $ 
cut -d " " -f 3 cut-sample.txt | tr "@" "$"
# james$gmail.com
cut -d " " -f 1 cut-sample.txt | tr -d "[.,]"

tr -d "[,.]" < cut-sample.txt 
# <  file redirect ( paste the file as input stdin )
```

## File Descriptor 

```bash
echo "hello world" > output.txt 
echo "hello " >> output 


date 1>success.log 2>error.log
date 1>mylog.log 2>mylog.log
date &>mylog.log 

```

### Reading file 
```bash
awk -F: '{print NR, $0}' /etc/passwd
awk 'NR>=17 && NR<=20 {print}' /etc/passwd
# show the line number with result 
awk 'NR>=17 && NR<=20 {print NR , $0}' /etc/passwd
head -20 /etc/passwd | tail -4
sed -n '17,20p' /etc/passwd

```