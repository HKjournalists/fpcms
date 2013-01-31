#!/bin/bash

source /etc/profile
pidfile=/tmp/jetty.pid
cmd="mvn jetty:run -Djetty.port=29999 -Duser.country=CN -Duser.language=zh"
run_user=www-data

cd /data/src/fpcms/fpcms/web-home


echo "kill app server by pid file:${pidfile}"
kill -9 `cat $pidfile`

echo "================================================================="
echo "  start server by cmd:${cmd} "
echo "================================================================="
nohup $cmd > /data/log/fpcms/jetty.log & 

hive_pid=$!
echo $hive_pid > $pidfile

