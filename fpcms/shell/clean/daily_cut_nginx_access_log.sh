#!/bin/bash

day=`date +%Y-%m-%d`
mv /data/log/nginx/access.log /data/log/nginx/access.log.${day}

# send sign for renew access log
kill -USR1 `cat /var/run/nginx.pid`

