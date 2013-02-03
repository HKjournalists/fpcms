package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupSelectorUtil {
	
	public static List<Element> selectList(Element doc, String... selector) {
		Elements elements = select(doc, selector);

		List<Element> allDiv = new ArrayList<Element>();
		for (Element element : elements) {
			allDiv.add(element);
		}
		return allDiv;
	}

	public static Elements select(Element doc, String... selectors) {
		if (selectors != null) {
			for (String selector : selectors) {
				if (StringUtils.isBlank(selector)) {
					continue;
				}

				Elements elements = doc.select(selector);
				if (elements.isEmpty()) {
					continue;
				}
				return elements;
			}
		}
		return new Elements();
	}
	
	
	public static class JsoupElementParentsSizeComparator implements Comparator<Element> {
		@Override
		public int compare(Element o1, Element o2) {
			int n1 = o1.parents().size();
			int n2 = o2.parents().size();
			return - new Integer(n1).compareTo(n2);
		}
	}
}