#!/bin/bash

source /etc/profile

bash /etc/profile

echo "select date(date_created) day,site,count(*) from cms_content group by day,site;" | ml fpcms

echo "select date(date_created) day,count(*) from cms_content group by day;" | ml fpcms



