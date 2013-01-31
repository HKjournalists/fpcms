alter table cms_channel modify channel_desc varchar(300);

update cms_channel set channel_desc = '${city}${company}代理有限公司于2005年挂牌成立,是经政府批准的具有${city}发票代开资格的专业税务${city}代开开票公司,可开${city}材料费发票|住宿费发票|${city}餐饮费发票|${city}酒店发票|广告费发票|等各类${city}|${city}增值税发票开发票项目'
where channel_code='home' ;

