/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : fpcms

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2012-12-21 19:04:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cms_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `cms_attachment`;
CREATE TABLE `cms_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `attachment` binary(255) DEFAULT NULL,
  `remarks` varchar(50) DEFAULT NULL,
  `date_last_modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `author` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for `cms_channel`
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_name` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '频道代码',
  `channel_desc` varchar(50) DEFAULT NULL COMMENT '频道描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父亲ID',
  `date_last_modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `site` varchar(50) DEFAULT NULL COMMENT '网站',
  `level` bigint(20) DEFAULT NULL COMMENT '等级',
  `content` text COMMENT '频道内容',
  `link` varchar(100) DEFAULT NULL COMMENT '链接',
  `link_target` varchar(30) DEFAULT NULL COMMENT '打开新窗口的模式',
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键字',
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_channel
-- ----------------------------
INSERT INTO `cms_channel` VALUES ('0', 'root', 'root', null, '-1', '2012-12-18 20:43:55', null, null, '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('10', '导航条', 'nav', '', '0', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('11', '产品', 'product', '产品列表', '0', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('12', '地区发票', 'area', '地区发票', '0', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('13', '发票新闻', 'category', '发票新闻', '0', '2012-12-20 22:59:18', '', '', '0', '', null, null, '', null);
INSERT INTO `cms_channel` VALUES ('14', '网站首页', 'home', '', '10', '2012-12-21 11:34:14', '', '', '0', '${default.company}于2005年挂牌树立,是经政府同意的首家具有${default.city}开发票，<span>${default.city}</span>代开发票资历的专业税务开公司。我司具有一支高素质、经验丰富、训练有素的高效率办税专业财税参谋部队。通过多年来的展开，与工商、税务、财政、海关、银行等部分及各地多家公司树立了亲近的协作关系，在全国各地开设了分公司。公司首要供给<span>${default.city}</span>开发票，发票征询，开记账，彻底触及全国事务。<br />\r\n<span>${default.company}</span>与全国各地首要城市都有事务协作公司，长时间承受财税署理事务，署理记帐、补帐、旧帐收拾、管帐（财政）征询。署理交税谋划征询，尽可能地获得“节税”的税收利益。<br />\r\n&nbsp; &nbsp; &nbsp;<span style=\"font-family:宋体;\">本公司本着为贵单位合理合法,节约运营成本和开支的, 在诚信保密的基础上,专业的理财。发票征询办理效劳范围：房屋租赁发票,税务机关发票一式三份,增值税专用发票，商品销售发票,餐饮发票,运输发票,广告发票,咨询发票,住宿发票,搬运发票,酒店服务发票,劳务费发票,建筑安装发票,加工修理发票,会议费发票,餐饮定额发票,租赁发票,服务发票 ,工业统一发票,商业统一发票,地方税控发票,等各行业发票等...</span><span style=\"font-family:宋体;\">！</span><br />\r\n<p>\r\n	&nbsp; &nbsp; &nbsp; 愿同各工商界携手、做交税人兄弟。恪守职业道德，诚挚为广大客户效劳，忠诚实行本身的效劳许诺。咱们拿手为委托人完成最小、合理的交税而进行描绘和运筹，协助客户躲避交税危险，下降公司税负，完成赢利最大化。&nbsp;\r\n</p>\r\n<p>\r\n	${default.keyword}\r\n</p>\r\n<br />', null, null, '', '2012-12-21 00:00:00');
INSERT INTO `cms_channel` VALUES ('15', '关于我们', 'aboutus', '', '10', '2012-12-19 20:06:34', '', '', '0', '<span style=\"font-family:Arial, Helvetica, sans-serif;font-size:18px;line-height:30px;background-color:#FFFFFF;\">${contract.company}</span><span style=\"color:#2F2F2F;font-family:Arial, Helvetica, sans-serif;font-size:18px;line-height:30px;background-color:#FFFFFF;\">有限公司是一家经财政局批准,工商注册登记&nbsp; 成立的财税顾问公司。具有雄厚的知识体系，丰富的操作经验，娴熟的沟通技巧。经过多年来的发展，与工商、税务、财政、海关、银行及政府等部门建立了非常密切的合作关系，以\"专业的精神，一流的服务\"为宗旨不断完善老客户，积极发展新客户，专业而热情的服务，赢得广大客户的好评。为了更好的适应广大客户的发展需要，我们将进一步完善发票公司的管理制度，优化服务内容，希望能成为您事业发展的强有力帮手。&nbsp;</span>', null, null, '', '2012-12-19 00:00:00');
INSERT INTO `cms_channel` VALUES ('16', '地区发票', 'province', '', '10', '2012-12-21 10:37:50', '', '', '0', '', null, null, '', null);
INSERT INTO `cms_channel` VALUES ('17', '财税咨询', 'consulting', '', '10', '2012-12-21 10:37:52', '', '', '0', '', null, null, '', null);
INSERT INTO `cms_channel` VALUES ('18', '开票项目', 'projects', '', '10', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('20', '联系方式', 'contact', '', '10', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('21', '广东发票', 'area_gd', '', '12', '2012-12-21 10:38:03', '', '', '0', '', null, null, '', null);
INSERT INTO `cms_channel` VALUES ('22', '餐饮发票', '', '', '13', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('23', '广告费发票', 'cat_ad', '', '13', '2012-12-20 23:28:20', '', '', '0', '', null, null, '', null);
INSERT INTO `cms_channel` VALUES ('24', '建筑发票', 'cat_building', '', '13', null, '', '', '0', '1111111', null, null, null, null);
INSERT INTO `cms_channel` VALUES ('25', '租赁费发票', '', '', '13', null, '', '', '0', '1111111111', null, null, null, null);
INSERT INTO `cms_channel` VALUES ('26', '会议费发票', '', '', '13', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('27', '代开其他各类发票', '', '', '13', null, '', '', '0', null, null, null, null, null);
INSERT INTO `cms_channel` VALUES ('28', '广告发票样板', '', '', '11', '2012-12-21 10:37:58', '', '', '0', '', null, null, '', null);
INSERT INTO `cms_channel` VALUES ('33', '浙江发票', 'area_zj', '', '12', '2012-12-20 23:33:23', '', '', '0', '', null, null, '', null);
INSERT INTO `cms_channel` VALUES ('34', '山东发票', 'area_sd', '', '12', '2012-12-21 10:38:14', '', '', '0', '', null, null, '', null);

-- ----------------------------
-- Table structure for `cms_content`
-- ----------------------------
DROP TABLE IF EXISTS `cms_content`;
CREATE TABLE `cms_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_code` varchar(50) DEFAULT NULL COMMENT '频道ID',
  `tags` varchar(50) DEFAULT NULL COMMENT '标签',
  `head_title` varchar(200) DEFAULT NULL COMMENT '网页头title',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `date_created` datetime DEFAULT NULL COMMENT '创建时间',
  `date_last_modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `site` varchar(50) DEFAULT NULL COMMENT '网站',
  `level` bigint(20) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_content
-- ----------------------------
INSERT INTO `cms_content` VALUES ('5', '', '', '', '', '<h2>\r\n	asssssssssss<span style=\"font-size:32px;\">sssssssss</span>ssaaaaaaaaaaaa\r\n</h2>\r\n<p>\r\n	<br />\r\n</p>\r\n<p>\r\n	asddddddddddddddd\r\n</p>', '', null, null, '', null);
INSERT INTO `cms_content` VALUES ('6', 'child-1', '', '', '', '', '', null, null, 'duowan.com', null);
INSERT INTO `cms_content` VALUES ('7', 'child-1', '111111111', '', '', '', '', null, null, 'duowan.com', null);
INSERT INTO `cms_content` VALUES ('8', '222222', '444444444', '', '', '', '', null, null, 'duowan.com', null);
INSERT INTO `cms_content` VALUES ('9', '222222', 'gggggggg', '', '', '', '', null, null, 'duowan.com', null);
INSERT INTO `cms_content` VALUES ('10', 'sub-1', '33333333', '', '', '', '', null, null, 'null', null);
INSERT INTO `cms_content` VALUES ('11', 'hhh', '', '', '', '', '', null, null, 'hhhhhhhhh', null);
INSERT INTO `cms_content` VALUES ('12', 'hhh', '', '', '', '', '', null, null, 'hhhhhhhhh', null);
INSERT INTO `cms_content` VALUES ('13', 'hhh', '222222', '', '', '', '', null, null, 'hhhhhhhhh', null);
INSERT INTO `cms_content` VALUES ('14', '222222', 'rrrrrrrrr', 'rrrrrrrrrrrrrrr', 'rrrrrrrrrrrr', '', '', null, null, 'null', null);
INSERT INTO `cms_content` VALUES ('15', '222222', 'rrrrrrrrrrrrr', 'rrrrrrrrrrrrrrr', '', '', '', null, null, 'null', null);
INSERT INTO `cms_content` VALUES ('16', '222222', 'fffffffffffffffff', 'fffffffffff', 'ffffffffffffff', '', '', null, null, 'null', null);
INSERT INTO `cms_content` VALUES ('29', 'home', '1111111111', '1111111111111', '', '<p>\r\n	cccccccccccccccc\r\n</p>\r\n<h2>\r\n	aaaaaaaaaaaaaa\r\n</h2>', '', '2012-12-20 10:59:00', '2012-12-20 10:59:00', '', null);
INSERT INTO `cms_content` VALUES ('30', 'home', 'title 11111111', 'head title', 'title', '<h2>\r\n	content xxxxxxxxxxxxx&nbsp;\r\n</h2>\r\n<p>\r\n	<br />\r\n</p>\r\n<p>\r\n	aaaaaaaaaa\r\n</p>', '', '2012-12-20 15:08:59', '2012-12-20 15:08:59', '', null);

-- ----------------------------
-- Table structure for `cms_property`
-- ----------------------------
DROP TABLE IF EXISTS `cms_property`;
CREATE TABLE `cms_property` (
  `prop_group` varchar(255) NOT NULL DEFAULT '',
  `prop_key` varchar(255) NOT NULL DEFAULT '',
  `prop_value` varchar(255) DEFAULT NULL,
  `ramarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prop_group`,`prop_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_property
-- ----------------------------
INSERT INTO `cms_property` VALUES ('', '', '', '');
INSERT INTO `cms_property` VALUES ('111111', '22222222', '', '');
INSERT INTO `cms_property` VALUES ('default', 'city', '苏州', '');
INSERT INTO `cms_property` VALUES ('default', 'company', '诚达通税务咨询有限公司', '');
INSERT INTO `cms_property` VALUES ('default', 'keyword', '苏州开发票', '');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', '111111111', '222222222', '', null);
INSERT INTO `sys_user` VALUES ('7', '', '', '', null);
