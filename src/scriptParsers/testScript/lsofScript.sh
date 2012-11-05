	#!/bin/sh
lsof  -p $1 |grep -E "(LISTEN|ESTABLISHED)"  |awk '{ printf $1 "\t" $2 "\t" $5 "\t" ;for(i=9;i<=NF;i++)printf $i" ";print""}'
