
-- Table "cms_site" DDL

CREATE TABLE `cms_site` (
  `site_domain` varchar(30) NOT NULL default '' COMMENT '��վ����',
  `site_name` varchar(100) default NULL COMMENT '��վ����',
  `site_desc` varchar(60) default NULL COMMENT '��վ����',
  `city` varchar(40) default NULL COMMENT '��վ��Ӧ�ĳ���',
  `keyword` varchar(120) default NULL COMMENT '��վ�ؼ���',
  `remarks` varchar(100) default NULL COMMENT '��ע',
  `company` varchar(50) default NULL COMMENT '��˾',
  `contact_name` varchar(50) default NULL COMMENT '��ϵ��',
  `mobile` varchar(20) default NULL COMMENT '�ƶ��绰',
  `qq` varchar(20) default NULL COMMENT 'QQ',
  `email` varchar(20) default NULL COMMENT '�ʼ�',
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

INSERT INTO `cms_site` VALUES ('www.aaafaipiao.com', '��ɽ', '', 'www', '��ɽ����Ʊ,��ɽ������Ʊ', '', '', '', '', '', 'fpqqchao@gmail.com', null, '2012-12-28 00:56:52', '');
INSERT INTO `cms_channel` VALUES ('1356785516046', '��ҳ', 'home', '��ҳ', '1', '2012-12-31 00:59:59', '', 'www.aaafaipiao.com', '90', '<div style=\"font-family:����;background-color:#FFFFFF;\">\r\n	<div class=\"neirong\" style=\"padding:0px 10px;\">\r\n		<span><span style=\"font-size:14px;line-height:21px;\"><b>&nbsp; &nbsp;</b></span></span><span style=\"font-size:10pt;\">�ϴ�ͨ˰����ѯ���޹�˾��2005���������,�Ǿ�����ͬ����׼Ҿ���localhost����Ʊ��localhost������Ʊ������רҵ˰�񿪹�˾����˾����һ֧�����ʡ�����ḻ��ѵ�����صĸ�Ч�ʰ�˰רҵ��˰��ѯ�Ŷӡ�ͨ����������չ�����빤�̡�˰�񡢲��������ء����еȲ��ּ����ض�ҹ�˾�����˽��ܵĺ�����ϵ����ȫ�����ؿ����˷ֹ�˾����˾��Ҫ����localhost����Ʊ����Ʊ��ѯ�������ˣ����״���ȫ������<br />\r\n�ϴ�ͨ˰����ѯ���޹�˾��ȫ��������Ҫ���ж�������Э����˾����ʱ����ܲ�˰��������������ʡ����ʡ�������ʰ�����ʣ���������ѯ������˰ı����ѯ�������ܵػ�á���˰����<a href=\"http://localhost:8080/fpcms/channel/show/news.do\">˰������</a>��<br />\r\n&nbsp; &nbsp; &nbsp;����˾����Ϊ��λ����Ϸ�,��Լ��Ӫ�ɱ��Ϳ�֧��, �ڳ��ű��ܵĻ�����,רҵ����ơ���Ʊ��ѯ����Ч�ͷ�Χ���������޷�Ʊ,˰����ط�Ʊһʽ����,��ֵ˰ר�÷�Ʊ����Ʒ���۷�Ʊ,������Ʊ,���䷢Ʊ,��淢Ʊ,��ѯ��Ʊ,ס�޷�Ʊ,���˷�Ʊ,�Ƶ����Ʊ,����ѷ�Ʊ,������װ��Ʊ,�ӹ�����Ʊ,����ѷ�Ʊ,�������Ʊ,���޷�Ʊ,����Ʊ ,��ҵͳһ��Ʊ,��ҵͳһ��Ʊ,�ط�˰�ط�Ʊ,�ȸ���ҵ��Ʊ��...��<br />\r\n		<p>\r\n			&nbsp; &nbsp; &nbsp; Ըͬ�����̽�Я�֡�����˰���ֵܡ����ְҵ���£���ֿΪ���ͻ�Ч�ͣ��ҳ�ʵ�б����Ч����ŵ����������Ϊί���������С������Ľ�˰�����������˳Э���ͻ���ܽ�˰Σ�գ��½���˾˰�������Ӯ����󻯡�&nbsp;\r\n		</p>\r\n</span>\r\n	</div>\r\n</div>', null, null, '', '2012-12-29 00:00:00');

/*
	public static CmsChannel NAV =  ROOT.newSubChannel(10,"nav","������");
	public static CmsChannel HOME =  ROOT.newSubChannel(20,"home","��ҳ");
	public static CmsChannel NEWS = ROOT.newSubChannel(30,"news","��������");
	public static CmsChannel[] NAV_SUB_CHANNELS =  {
		NAV.newSubChannel(1010,"aboutus","��������"),
		NAV.newSubChannel(1020,"projects","��Ʊ��Ŀ"),
		NAV.newSubChannel(1040,"contact","��ϵ��ʽ"),
*/
UPDATE cms_channel set id = 10 where channel_code='nav';
UPDATE cms_channel set id = 20 where channel_code='home';
UPDATE cms_channel set id = 30 where channel_code='news';
UPDATE cms_channel set id = 1010 where channel_code='aboutus';
UPDATE cms_channel set id = 1020 where channel_code='projects';
UPDATE cms_channel set id = 1040 where channel_code='contact';

