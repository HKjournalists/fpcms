/*
MySQL Data Transfer
Source Host: localhost
Source Database: fpcms
Target Host: localhost
Target Database: fpcms
Date: 2012-12-27 1:45:31
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cms_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_site`;
CREATE TABLE `cms_site` (
  `site_domain` varchar(30) NOT NULL default '' COMMENT '网站域名',
  `site_name` varchar(100) default NULL COMMENT '网站名称',
  `site_desc` varchar(60) default NULL COMMENT '网站描述',
  `site_city` varchar(40) default NULL COMMENT '网站对应的城市',
  `site_keyword` varchar(120) default NULL COMMENT '网站关键词',
  `remarks` varchar(100) default NULL COMMENT '备注',
  PRIMARY KEY  (`site_domain`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `cms_site` VALUES ('ah.aaafaipiao.com', '安徽', null, 'ah', '安徽开发票 安徽代开发票', null);
INSERT INTO `cms_site` VALUES ('bj.aaafaipiao.com', '北京', null, 'bj', '北京开发票 北京代开发票', null);
INSERT INTO `cms_site` VALUES ('bt.aaafaipiao.com', '包头', null, 'bt', '包头开发票 包头代开发票', null);
INSERT INTO `cms_site` VALUES ('cd.aaafaipiao.com', '成都', null, 'cd', '成都开发票 成都代开发票', null);
INSERT INTO `cms_site` VALUES ('cz.aaafaipiao.com', '常州', null, 'cz', '常州开发票 常州代开发票', null);
INSERT INTO `cms_site` VALUES ('dg.aaafaipiao.com', '东莞', null, 'dg', '东莞开发票 东莞代开发票', null);
INSERT INTO `cms_site` VALUES ('dl.aaafaipiao.com', '大连', null, 'dl', '大连开发票 大连代开发票', null);
INSERT INTO `cms_site` VALUES ('dq.aaafaipiao.com', '大庆', null, 'dq', '大庆开发票 大庆代开发票', null);
INSERT INTO `cms_site` VALUES ('dy.aaafaipiao.com', '东营', null, 'dy', '东营开发票 东营代开发票', null);
INSERT INTO `cms_site` VALUES ('eeds.aaafaipiao.com', '鄂尔多斯', null, 'eeds', '鄂尔多斯开发票 鄂尔多斯代开发票', null);
INSERT INTO `cms_site` VALUES ('fj.aaafaipiao.com', '福建', null, 'fj', '福建开发票 福建代开发票', null);
INSERT INTO `cms_site` VALUES ('fs.aaafaipiao.com', '佛山', null, 'fs', '佛山开发票 佛山代开发票', null);
INSERT INTO `cms_site` VALUES ('fz.aaafaipiao.com', '福州', null, 'fz', '福州开发票 福州代开发票', null);
INSERT INTO `cms_site` VALUES ('gd.aaafaipiao.com', '广东', null, 'gd', '广东开发票 广东代开发票', null);
INSERT INTO `cms_site` VALUES ('gz.aaafaipiao.com', '广州', null, 'gz', '广州开发票 广州代开发票', null);
INSERT INTO `cms_site` VALUES ('hb.aaafaipiao.com', '湖北', null, 'hb', '湖北开发票 湖北代开发票', null);
INSERT INTO `cms_site` VALUES ('hb1.aaafaipiao.com', '河北', null, 'hb1', '河北开发票 河北代开发票', null);
INSERT INTO `cms_site` VALUES ('hd.aaafaipiao.com', '邯郸', null, 'hd', '邯郸开发票 邯郸代开发票', null);
INSERT INTO `cms_site` VALUES ('heb.aaafaipiao.com', '哈尔滨', null, 'heb', '哈尔滨开发票 哈尔滨代开发票', null);
INSERT INTO `cms_site` VALUES ('hf.aaafaipiao.com', '合肥', null, 'hf', '合肥开发票 合肥代开发票', null);
INSERT INTO `cms_site` VALUES ('hlj.aaafaipiao.com', '黑龙江', null, 'hlj', '黑龙江开发票 黑龙江代开发票', null);
INSERT INTO `cms_site` VALUES ('hn.aaafaipiao.com', '湖南', null, 'hn', '湖南开发票 湖南代开发票', null);
INSERT INTO `cms_site` VALUES ('hn1.aaafaipiao.com', '河南', null, 'hn1', '河南开发票 河南代开发票', null);
INSERT INTO `cms_site` VALUES ('hz.aaafaipiao.com', '杭州', null, 'hz', '杭州开发票 杭州代开发票', null);
INSERT INTO `cms_site` VALUES ('jl.aaafaipiao.com', '吉林', null, 'jl', '吉林开发票 吉林代开发票', null);
INSERT INTO `cms_site` VALUES ('jn.aaafaipiao.com', '济南', null, 'jn', '济南开发票 济南代开发票', null);
INSERT INTO `cms_site` VALUES ('jn1.aaafaipiao.com', '济宁', null, 'jn1', '济宁开发票 济宁代开发票', null);
INSERT INTO `cms_site` VALUES ('js.aaafaipiao.com', '江苏', null, 'js', '江苏开发票 江苏代开发票', null);
INSERT INTO `cms_site` VALUES ('jx.aaafaipiao.com', '嘉兴', null, 'jx', '嘉兴开发票 嘉兴代开发票', null);
INSERT INTO `cms_site` VALUES ('jx1.aaafaipiao.com', '江西', null, 'jx1', '江西开发票 江西代开发票', null);
INSERT INTO `cms_site` VALUES ('ln.aaafaipiao.com', '辽宁', null, 'ln', '辽宁开发票 辽宁代开发票', null);
INSERT INTO `cms_site` VALUES ('ly.aaafaipiao.com', '洛阳', null, 'ly', '洛阳开发票 洛阳代开发票', null);
INSERT INTO `cms_site` VALUES ('ly1.aaafaipiao.com', '临沂', null, 'ly1', '临沂开发票 临沂代开发票', null);
INSERT INTO `cms_site` VALUES ('nb.aaafaipiao.com', '宁波', null, 'nb', '宁波开发票 宁波代开发票', null);
INSERT INTO `cms_site` VALUES ('nc.aaafaipiao.com', '南昌', null, 'nc', '南昌开发票 南昌代开发票', null);
INSERT INTO `cms_site` VALUES ('nj.aaafaipiao.com', '南京', null, 'nj', '南京开发票 南京代开发票', null);
INSERT INTO `cms_site` VALUES ('nmg.aaafaipiao.com', '内蒙古', null, 'nmg', '内蒙古开发票 内蒙古代开发票', null);
INSERT INTO `cms_site` VALUES ('nt.aaafaipiao.com', '南通', null, 'nt', '南通开发票 南通代开发票', null);
INSERT INTO `cms_site` VALUES ('qd.aaafaipiao.com', '青岛', null, 'qd', '青岛开发票 青岛代开发票', null);
INSERT INTO `cms_site` VALUES ('qz.aaafaipiao.com', '泉州', null, 'qz', '泉州开发票 泉州代开发票', null);
INSERT INTO `cms_site` VALUES ('sc.aaafaipiao.com', '四川', null, 'sc', '四川开发票 四川代开发票', null);
INSERT INTO `cms_site` VALUES ('sd.aaafaipiao.com', '山东', null, 'sd', '山东开发票 山东代开发票', null);
INSERT INTO `cms_site` VALUES ('sh.aaafaipiao.com', '上海', null, 'sh', '上海开发票 上海代开发票', null);
INSERT INTO `cms_site` VALUES ('sjz.aaafaipiao.com', '石家庄', null, 'sjz', '石家庄开发票 石家庄代开发票', null);
INSERT INTO `cms_site` VALUES ('sx.aaafaipiao.com', '陕西', null, 'sx', '陕西开发票 陕西代开发票', null);
INSERT INTO `cms_site` VALUES ('sx1.aaafaipiao.com', '绍兴', null, 'sx1', '绍兴开发票 绍兴代开发票', null);
INSERT INTO `cms_site` VALUES ('sy.aaafaipiao.com', '沈阳', null, 'sy', '沈阳开发票 沈阳代开发票', null);
INSERT INTO `cms_site` VALUES ('sz.aaafaipiao.com', '深圳', null, 'sz', '深圳开发票 深圳代开发票', null);
INSERT INTO `cms_site` VALUES ('sz1.aaafaipiao.com', '苏州', null, 'sz1', '苏州开发票 苏州代开发票', null);
INSERT INTO `cms_site` VALUES ('tj.aaafaipiao.com', '天津', null, 'tj', '天津开发票 天津代开发票', null);
INSERT INTO `cms_site` VALUES ('ts.aaafaipiao.com', '唐山', null, 'ts', '唐山开发票 唐山代开发票', null);
INSERT INTO `cms_site` VALUES ('tz.aaafaipiao.com', '台州', null, 'tz', '台州开发票 台州代开发票', null);
INSERT INTO `cms_site` VALUES ('wf.aaafaipiao.com', '潍坊', null, 'wf', '潍坊开发票 潍坊代开发票', null);
INSERT INTO `cms_site` VALUES ('wh.aaafaipiao.com', '武汉', null, 'wh', '武汉开发票 武汉代开发票', null);
INSERT INTO `cms_site` VALUES ('wx.aaafaipiao.com', '无锡', null, 'wx', '无锡开发票 无锡代开发票', null);
INSERT INTO `cms_site` VALUES ('wz.aaafaipiao.com', '温州', null, 'wz', '温州开发票 温州代开发票', null);
INSERT INTO `cms_site` VALUES ('xa.aaafaipiao.com', '西安', null, 'xa', '西安开发票 西安代开发票', null);
INSERT INTO `cms_site` VALUES ('xz.aaafaipiao.com', '徐州', null, 'xz', '徐州开发票 徐州代开发票', null);
INSERT INTO `cms_site` VALUES ('yc.aaafaipiao.com', '盐城', null, 'yc', '盐城开发票 盐城代开发票', null);
INSERT INTO `cms_site` VALUES ('yt.aaafaipiao.com', '烟台', null, 'yt', '烟台开发票 烟台代开发票', null);
INSERT INTO `cms_site` VALUES ('yz.aaafaipiao.com', '扬州', null, 'yz', '扬州开发票 扬州代开发票', null);
INSERT INTO `cms_site` VALUES ('zb.aaafaipiao.com', '淄博', null, 'zb', '淄博开发票 淄博代开发票', null);
INSERT INTO `cms_site` VALUES ('zc.aaafaipiao.com', '长春', null, 'zc', '长春开发票 长春代开发票', null);
INSERT INTO `cms_site` VALUES ('zj.aaafaipiao.com', '浙江', null, 'zj', '浙江开发票 浙江代开发票', null);
INSERT INTO `cms_site` VALUES ('zq.aaafaipiao.com', '重庆', null, 'zq', '重庆开发票 重庆代开发票', null);
INSERT INTO `cms_site` VALUES ('zs.aaafaipiao.com', '长沙', null, 'zs', '长沙开发票 长沙代开发票', null);
INSERT INTO `cms_site` VALUES ('zz.aaafaipiao.com', '郑州', null, 'zz', '郑州开发票 郑州代开发票', null);
