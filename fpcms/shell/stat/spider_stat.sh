#!/bin/bash


function spider_stat() {
	local spider=$1
	local site=$2
	echo ""
	echo ""
	echo ""
	echo "----------- $spider : $site Visit stat -----------"
	cd /data/log/fpcms
	cat spider.log | grep ${spider} | grep $site | awk '{print $4}' | sort | uniq -c
        cat spider.log* | grep ${spider} | grep $site | awk '{a[$1]=a[$1]+1} END {for(item in a) print item" "a[item] }' | sort
}


spider_stat Google aaafaipiao.com
spider_stat Baidu fpzhangsha.com
spider_stat Baidu fpshamen.com
spider_stat Baidu fpzhuhai.com
spider_stat Baidu fpshijiazhuang.com
spider_stat Baidu fpjinan.com
spider_stat Baidu fpfuzhou.com
spider_stat Baidu haofapiao.com
spider_stat Baidu starkp.com
spider_stat Baidu szhaoexport.com
spider_stat Baidu fpnanchang.com
spider_stat Baidu powerkp.info
spider_stat Baidu goodk123.info
spider_stat Baidu bbbkp123.info
spider_stat Baidu 6699ysk.info
spider_stat Baidu 1111kp.info

spider_stat Baidu kp1111.info
spider_stat Baidu kp1234.info
spider_stat Baidu fp1234.info
spider_stat Baidu star1111.info
spider_stat Baidu star1234.info
spider_stat Baidu fp1111.info

spider_stat Baidu aaafaipiao.com

cat /data/log/fpcms/spider.log | grep Baidu | grep  -P "\w+.\w+/linked" 

echo ""
cat /data/log/fpcms/spider.log | awk '{print $6}' | sort | uniq -c

echo ""
echo "/data/log/fpcms/spider.log"
tail -n 30 /data/log/fpcms/spider.log

