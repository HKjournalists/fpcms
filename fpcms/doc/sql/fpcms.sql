/*
MySQL Data Transfer
Source Host: localhost
Source Database: fpcms
Target Host: localhost
Target Database: fpcms
Date: 2012-12-18 9:15:13
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cms_attachment
-- ----------------------------
DROP TABLE IF EXISTS `cms_attachment`;
CREATE TABLE `cms_attachment` (
  `id` bigint(20) NOT NULL default '0',
  `code` varchar(50) default NULL,
  `attachment` binary(255) default NULL,
  `remarks` varchar(50) default NULL,
  `date_last_modified` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `author` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_channel
-- ----------------------------
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `id` bigint(20) NOT NULL auto_increment,
  `channel_name` varchar(50) default NULL COMMENT '频道名称',
  `channel_code` varchar(50) default NULL COMMENT '频道代码',
  `channel_desc` varchar(50) default NULL COMMENT '频道描述',
  `parent_id` bigint(20) default NULL COMMENT '父亲ID',
  `date_last_modified` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `author` varchar(50) default NULL COMMENT '作者',
  `site` varchar(50) default NULL COMMENT '网站',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_content
-- ----------------------------
DROP TABLE IF EXISTS `cms_content`;
CREATE TABLE `cms_content` (
  `id` bigint(20) NOT NULL auto_increment,
  `channel_code` varchar(50) default NULL COMMENT '频道ID',
  `tags` varchar(50) default NULL COMMENT '标签',
  `head_title` varchar(200) default NULL COMMENT '网页头title',
  `title` varchar(200) default NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `author` varchar(50) default NULL COMMENT '作者',
  `date_created` datetime default NULL COMMENT '创建时间',
  `date_last_modified` timestamp NULL default NULL on update CURRENT_TIMESTAMP COMMENT '更新时间',
  `site` varchar(50) default NULL COMMENT '网站',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for cms_property
-- ----------------------------
DROP TABLE IF EXISTS `cms_property`;
CREATE TABLE `cms_property` (
  `prop_group` varchar(255) NOT NULL default '',
  `prop_key` varchar(255) NOT NULL default '',
  `prop_value` varchar(255) default NULL,
  `ramarks` varchar(255) default NULL,
  PRIMARY KEY  (`prop_group`,`prop_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL auto_increment,
  `username` varchar(50) default NULL,
  `password` varchar(50) default NULL,
  `remark` varchar(255) default NULL,
  `enabled` tinyint(4) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records 
-- ----------------------------
