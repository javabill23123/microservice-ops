#! /bin/env bash
df |sed -n '2,20p'|grep -v "^$"|awk '{print "ddiskcap,filesystem="$1 ",path="$6 " size="$2 ",used="$3 ",avail="$4 ",used_percent="$5 }' >/tmp/diskcap.txt
while read line
do
  echo $line |awk -F "%" '{print  $1}'
done < /tmp/diskcap.txt



iowstat=`sar -b 1 1|grep Average |awk '{print "diostat,name=iostat tps=" $2 ",rtps=" $3 ",wtps=" $4 ",bread/s=" $5 ",bwrtn/s=" $6}'`
echo $iowstat

pos=`iostat -x|grep -A10 Device|head -1|awk '{for(i=1;i<=NF;i++)if($i~/^[%u]/)print i}'`
iostat -x|grep -A10 Device|sed -n '2,10p'|grep -v "^$"|awk '{printf "ddeviceio,name=iostat" NR " device=\"" $1 "\",rrqm/s=" $2 ",wrqm/s=" $3 ",rkB/s="$6 ",wkB/s="$7 ",await=" $10 ",util_percent=" $p "\n"}' p="$pos"
