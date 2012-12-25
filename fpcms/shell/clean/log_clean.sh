#!/bin/bash

#
# clean before n days logs
#

source /etc/profile

/usr/bin/find /data/log -mtime +10 -name "*.log.*" -exec /bin/rm -rf {} \; 

