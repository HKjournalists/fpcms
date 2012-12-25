#!/bin/bash

source /etc/profile

bak_dir=/data/backup/mysql
day=`date -d "-0 day" +"%Y-%m-%d"`
bak_db=fpcms
bak_days=45

mkdir -p $bak_dir
mysqldump -ubackup -phibernate -h198.148.120.10 --default-character-set=utf8 --lock-tables=false ${bak_db}  | gzip > ${bak_dir}/${bak_db}_${day}.sql.gz

echo "backup db ${bak_db} end"

echo "clean ${bak_days} days before backup files"
/usr/bin/find ${bak_dir} -mtime +${bak_days} -name "${bak_db}_*.sql.gz" -exec /bin/rm -rf {} \; 


