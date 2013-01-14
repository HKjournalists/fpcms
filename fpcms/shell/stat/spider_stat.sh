#!/bin/bash


function spider_stat() {
	local spider=$1
	echo ""
	echo ""
	echo ""
	echo "----------- $spider Visit stat -----------"
	cat /data/log/fpcms/spider.log | grep ${spider} | awk '{print $4}' | sort | uniq -c
        cat /data/log/fpcms/spider.log* | grep ${spider} | awk '{a[$1]=a[$1]+1} END {for(item in a) print item" "a[item] }' | sort

}


spider_stat Google
spider_stat Baidu

echo ""
echo ""
echo "/data/log/fpcms/spider.log"
tail -n 30 /data/log/fpcms/spider.log

