#! /bin/env bash
load=`uptime |awk -F 'average: ' '{print $2}'|awk -F ', '  '{print "cpuload,name=load load1=" $1 ",load2=" $2 ",load3="$3}'`
echo $load