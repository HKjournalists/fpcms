#!/bin/bash

source /etc/profile

bash /etc/profile

echo "select date(date_created) day,site,count(*) from cms_content group by day,site;" | ml fpcms

echo "select date(date_created) day,count(*) from cms_content group by day;" | ml fpcms


select date(date_created) day,site,count(*) from cms_content where date_created  > date_sub(current_date(), interval 5 day)  group by day,site order by site desc,day desc;
select date(date_created) day,count(*) from cms_content where date_created  > date_sub(current_date(), interval 30 day)  group by day order by day desc;
select date(date_created) day,site,count(*) from cms_content where date_created  > date_sub(current_date(), interval 1 day)  group by day,site order by day desc;


