#! /bin/env bash
sar -n DEV |grep Average:|awk '{print "dnetstat,interface=" $2 " rxpck/s="$3 ",txpck/s=" $4 ",rxkB/s=" $5 ",txkB/s=" $6 }'
