#!/bin/bash


function spider_stat() {
	local spider=$1
	echo ""
	echo ""
	echo ""
	echo "----------- $spider Visit stat -----------"
	cat /data/log/fpcms/spider.log | grep ${spider} | awk '{print $4}' | sort | uniq
}

spider_stat Baidu
spider_stat Google

echo ""
echo ""
echo "/data/log/fpcms/spider.log"
tail -n 30 /data/log/fpcms/spider.log

