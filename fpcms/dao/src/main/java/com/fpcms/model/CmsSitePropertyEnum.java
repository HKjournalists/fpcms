package com.fpcms.model;

import com.duowan.common.lang.enums.EnumBase;

public enum CmsSitePropertyEnum implements EnumBase{
	PROP_KEYWORDS_RANK_BAIDU("keywordsRankBaidu","百度关键字排名"),
	PROP_EXPECT_CONTENT_TAGS("exceptContentTags","期待的网站内容tag"),
	WEB_META_VERIFY_CODE("webMetaVerifyCode","验证网站归属的Html Head Meta"),
	WEBSITE_STAT_CODE("websiteStatCode","网站访问统计代码"),
	;
	
	private final String code;
	private final String desc;
	
	CmsSitePropertyEnum(String code,String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
	
}
