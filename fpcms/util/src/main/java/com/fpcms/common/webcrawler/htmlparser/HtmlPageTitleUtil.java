package com.fpcms.common.webcrawler.htmlparser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;

import com.fpcms.common.util.KeywordUtil;
import com.fpcms.common.util.StringLengthComparator;
import com.fpcms.common.util.TextLangUtil;
import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;

public class HtmlPageTitleUtil {
	public static String smartGetTitle(Anchor anchor, String pageTitle) {
		if(StringUtils.isNotBlank(anchor.getText()) && anchor.getText().length() >= 6) {
			return extrectMainTitle(pageTitle,true,anchor.getText().length());
		}
		
		return filterWithMaxLength(smartGetTitle(pageTitle));
	}
	
	public static String filterWithMaxLength(String title) {
		if(TextLangUtil.hasChinese(title)) {
			List<String> keywordsList = KeywordUtil.toTokenizerList(title);
			Collections.sort(keywordsList,new ReverseComparator(new StringLengthComparator()));
			String maxLengthKeyword = keywordsList.get(0);
			return maxLengthKeyword;
		}else {
			return title;
		}
	}

	public static String smartGetTitle(String pageTitle) {
		String result = extrectMainTitle(pageTitle,true,0);
		
		//english
		if(pageTitle.matches("[\\s\\w-_:|]+")) {
			ArrayList<String> tokenizerList = KeywordUtil.toTokenizerList(result);
			if(tokenizerList.size() < 5) {
				return extrectMainTitle(pageTitle,false,pageTitle.length());
			}
		}else {
			if(result.length() < 5) {
				return extrectMainTitle(pageTitle,false,pageTitle.length());
			}
		}
		
		return result;
	}

	private static char[] titleSeperator = {'_','-',':','|','>','：','—','-','|'};
	static String extrectMainTitle(String title,boolean isIndexOf,int fromIndex) {
		title = title.trim();
		for(char c : titleSeperator) {
			int indexOf = isIndexOf ? title.indexOf(c,fromIndex) : title.lastIndexOf(c, fromIndex);
			if(indexOf >= 0) {
				return title.substring(0,indexOf).trim();
			}
		}
		return title;
	}
}