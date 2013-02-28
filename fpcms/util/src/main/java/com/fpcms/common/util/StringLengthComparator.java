package com.fpcms.common.util;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		return new Integer(o1.length()).compareTo(o2.length());
	}

}
