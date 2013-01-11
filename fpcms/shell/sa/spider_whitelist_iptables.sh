#!/bin/bash

source /etc/profile

cd /data/log/fpcms
ip_list=`cat spider.log | awk '{print $5}' | sort | uniq`

for ip in ip_list
	iptables -I INPUT -s $ip -p tcp --dport 80 -j ACCEPT
done

