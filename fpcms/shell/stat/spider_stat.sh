#!/bin/bash


function spider_stat() {
	local spider=$1
	local site=$2
	echo ""
	echo ""
	echo ""
	echo "----------- $spider : $site Visit stat -----------"
	cat /data/log/fpcms/spider.log | grep ${spider} | grep $site | awk '{print $4}' | sort | uniq -c
        cat /data/log/fpcms/spider.log* | grep ${spider} | grep $site | awk '{a[$1]=a[$1]+1} END {for(item in a) print item" "a[item] }' | sort

}


spider_stat Google aaafaipiao.com
spider_stat Baidu fpzhangsha.com
spider_stat Baidu fpshamen.com
spider_stat Baidu fpzhuhai.com
spider_stat Baidu fpshijiazhuang.com
spider_stat Baidu fpjinan.com
spider_stat Baidu fpfuzhou.com
spider_stat Baidu haofapiao.com
spider_stat Baidu aaafaipiao.com

echo ""
echo ""
echo "/data/log/fpcms/spider.log"
tail -n 30 /data/log/fpcms/spider.log

