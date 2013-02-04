package com.fpcms.common.webcrawler.htmlparser;

import com.fpcms.common.webcrawler.htmlparser.HtmlPage.Anchor;

public interface HtmlPageCrawler {
	
	public boolean shoudVisitPage(Anchor a);
	
	public void visit(HtmlPage page);
	
}
