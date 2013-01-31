#!/bin/bash

#
# clean before n days logs
#

source /etc/profile

/usr/bin/find /data/log -mtime +30 -name "*.log.*" -exec /bin/rm -rf {} \; 

