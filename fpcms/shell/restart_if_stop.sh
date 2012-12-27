#!/bin/bash

source /etc/profile

pidfile=/tmp/jetty.pid

if [ -s $pidfile ] && [ -d /proc/`cat $pidfile` ]; then
        echo "jetty app server running,then do nothing"
else
        echo "jetty app server is stop,then restart"
        /data/app/fpcms/shell/deploy.sh
fi

