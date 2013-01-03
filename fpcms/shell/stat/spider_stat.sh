#!/bin/bash

echo "----------- Baidu Visit stat -----------"
cat /data/log/fpcms/spider.log | grep baidu | awk '{print $4}' | sort | uniq

echo ""
echo ""
echo ""
echo "----------- Google Visit stat -----------"
cat /data/log/fpcms/spider.log | grep google | awk '{print $4}' | sort | uniq


echo "/data/log/fpcms/spider.log"

tail -n 30 /data/log/fpcms/spider.log

