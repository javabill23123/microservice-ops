#! /bin/env bash
phytable="dmemory,name=memory "
phytotal=`cat /proc/meminfo|grep MemTotal|awk '{print $2/1024}'`
phyfree=`cat /proc/meminfo|grep MemFree|awk '{print  $2/1024}'`
phyused=`echo "scale=3;$phytotal-$phyfree"|bc`
phyoth=`vmstat |tail -1 |awk '{print "swpd=" $3/1024 ",buff=" $5/1024 ",cache=" $6/1024}'`
echo $phytable total=$phytotal,free=$phyfree,used=$phyused,$phyoth

swaptable="dswapmemory,name=swapmemory "
swaptotal=`cat /proc/meminfo|grep SwapTotal|awk '{print $2/1024}'`
swapfree=`cat /proc/meminfo|grep SwapFree|awk '{print  $2/1024}'`
swapused=`echo "scale=3;$swaptotal-$swapfree"|bc`
swapoth=`vmstat |tail -1 |awk '{print "si=" $7/1024 ",so=" $8/1024 }'`
echo $swaptable total=$swaptotal,free=$swapfree,used=$swapused,$swapoth