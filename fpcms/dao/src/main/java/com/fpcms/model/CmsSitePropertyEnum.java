package com.fpcms.model;

public enum CmsSitePropertyEnum {
	PROP_KEYWORDS_RANK_BAIDU("keywordsRankBaidu","百度关键字排名"),
	PROP_EXPECT_CONTENT_TAGS("exceptContentTags","期待的网站内容tag");
	
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
