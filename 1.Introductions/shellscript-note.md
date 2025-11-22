
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