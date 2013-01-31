ALTER TABLE `cms_content`
ADD COLUMN `search_keyword`  varchar(40) NULL COMMENT '搜索引擎检索的关键字' AFTER `level`,
ADD INDEX `index_search_keyword` (`search_keyword`) ;


ALTER TABLE `cms_site`
ADD COLUMN `ip`  varchar(16) NULL COMMENT 'IP地址' AFTER `props`,
ADD COLUMN `http_status`  varchar(40) NULL COMMENT 'HTTP端口状态' AFTER `ip`;



CREATE TABLE `cms_domain` (
  `domain` varchar(30) NOT NULL DEFAULT '' COMMENT '主域名',
  `remarks` varchar(200) DEFAULT NULL COMMENT '备注',
  `props` varchar(2000) DEFAULT NULL COMMENT '扩展key_value属性',
  `ip` varchar(200) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`domain`),
  UNIQUE KEY `index_remarks` (`remarks`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

