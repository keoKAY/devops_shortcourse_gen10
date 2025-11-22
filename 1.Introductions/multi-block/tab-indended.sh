#!/bin/bash

echo "[+] Writing the configuration as text block 👍"

cat <<-EOF > output.txt
	Tabbed indentation removed.
    Tab again. 
    Another line. 
EOF

