
-- Table "cms_site" DDL

CREATE TABLE `cms_site` (
  `site_domain` varchar(30) NOT NULL default '' COMMENT '网站域名',
  `site_name` varchar(100) default NULL COMMENT '网站名称',
  `site_desc` varchar(60) default NULL COMMENT '网站描述',
  `city` varchar(40) default NULL COMMENT '网站对应的城市',
  `keyword` varchar(120) default NULL COMMENT '网站关键词',
  `remarks` varchar(100) default NULL COMMENT '备注',
  `company` varchar(50) default NULL COMMENT '公司',
  `contact_name` varchar(50) default NULL COMMENT '联系人',
  `mobile` varchar(20) default NULL COMMENT '移动电话',
  `qq` varchar(20) default NULL COMMENT 'QQ',
  `email` varchar(20) default NULL COMMENT '邮件',
  `date_created` datetime default NULL,
  `date_last_modified` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `author` varchar(30) default NULL,
  PRIMARY KEY  (`site_domain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





update cms_channel set site='www.aaafaipiao.com';
update cms_content set site='www.aaafaipiao.com';
update cms_property set prop_group='www.aaafaipiao.com';

ALTER TABLE cms_channel change id id bigint not null;
ALTER TABLE cms_channel DROP PRIMARY KEY;
ALTER TABLE cms_channel ADD PRIMARY KEY  (id,site); 

INSERT INTO `cms_site` VALUES ('www.aaafaipiao.com', '唐山', '', 'www', '唐山开发票,唐山代开发票', '', '', '', '', '', 'fpqqchao@gmail.com', null, '2012-12-28 00:56:52', '');
INSERT INTO `cms_channel` VALUES ('1356785516046', '首页', 'home', '首页', '1', '2012-12-31 00:59:59', '', 'www.aaafaipiao.com', '90', '<div style=\"font-family:宋体;background-color:#FFFFFF;\">\r\n	<div class=\"neirong\" style=\"padding:0px 10px;\">\r\n		<span><span style=\"font-size:14px;line-height:21px;\"><b>&nbsp; &nbsp;</b></span></span><span style=\"font-size:10pt;\">诚达通税务咨询有限公司于2005年挂牌树立,是经政府同意的首家具有localhost开发票，localhost代开发票资历的专业税务开公司。我司具有一支高素质、经验丰富、训练有素的高效率办税专业财税咨询团队。通过多年来的展开，与工商、税务、财政、海关、银行等部分及各地多家公司树立了紧密的合作关系，在全国各地开设了分公司。公司首要供给localhost开发票，发票征询，开记账，彻底触及全国事务。<br />\r\n诚达通税务咨询有限公司与全国各地首要城市都有事务协作公司，长时间承受财税署理事务，署理记帐、补帐、旧帐收拾、管帐（财政）征询。署理交税谋划征询，尽可能地获得“节税”的<a href=\"http://localhost:8080/fpcms/channel/show/news.do\">税收利益</a>。<br />\r\n&nbsp; &nbsp; &nbsp;本公司本着为贵单位合理合法,节约运营成本和开支的, 在诚信保密的基础上,专业的理财。发票征询办理效劳范围：房屋租赁发票,税务机关发票一式三份,增值税专用发票，商品销售发票,餐饮发票,运输发票,广告发票,咨询发票,住宿发票,搬运发票,酒店服务发票,劳务费发票,建筑安装发票,加工修理发票,会议费发票,餐饮定额发票,租赁发票,服务发票 ,工业统一发票,商业统一发票,地方税控发票,等各行业发票等...！<br />\r\n		<p>\r\n			&nbsp; &nbsp; &nbsp; 愿同各工商界携手、做交税人兄弟。恪守职业道德，诚挚为广大客户效劳，忠诚实行本身的效劳许诺。咱们拿手为委托人完成最小、合理的交税而进行描绘和运筹，协助客户躲避交税危险，下降公司税负，完成赢利最大化。&nbsp;\r\n		</p>\r\n</span>\r\n	</div>\r\n</div>', null, null, '', '2012-12-29 00:00:00');

/*
	public static CmsChannel NAV =  ROOT.newSubChannel(10,"nav","导航条");
	public static CmsChannel HOME =  ROOT.newSubChannel(20,"home","首页");
	public static CmsChannel NEWS = ROOT.newSubChannel(30,"news","新闻中心");
	public static CmsChannel[] NAV_SUB_CHANNELS =  {
		NAV.newSubChannel(1010,"aboutus","关于我们"),
		NAV.newSubChannel(1020,"projects","开票项目"),
		NAV.newSubChannel(1040,"contact","联系方式"),
*/
UPDATE cms_channel set id = 10 where channel_code='nav';
UPDATE cms_channel set id = 20 where channel_code='home';
UPDATE cms_channel set id = 30 where channel_code='news';
UPDATE cms_channel set id = 1010 where channel_code='aboutus';
UPDATE cms_channel set id = 1020 where channel_code='projects';
UPDATE cms_channel set id = 1040 where channel_code='contact';

