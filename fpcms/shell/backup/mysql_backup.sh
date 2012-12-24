#!/bin/bash

day=`date -d "-0 day" +"%Y-%m-%d"`

source /etc/profile
mkdir -p /data/backup/mysql
mysqldump -uroot -p123456789 fpcms | gzip > /data/backup/mysql/fpcms_${day}.sql.gz


