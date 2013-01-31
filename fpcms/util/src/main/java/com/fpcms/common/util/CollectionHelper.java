package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionHelper {
	
	public static <T> List<List<T>> toMultiRows(Collection<T> list,int cols) {
		List<List<T>> result = new ArrayList<List<T>>();
		
		Iterator<T> it = list.iterator();
		while(it.hasNext()) {
			ArrayList<T> row = new ArrayList<T>();
			for(int i = 0; i < cols && it.hasNext(); i++) {
				row.add(it.next());
			}
			result.add(row);
		}
		return result;
	}
	
}
