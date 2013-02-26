package com.fpcms.common.util;

import org.junit.Assert;
import org.junit.Test;


public class JChineseConvertorTest extends Assert{

	@Test
	public void test() {
		assertEquals("财政部税务入口网统一发票管理",JChineseConvertor.getInstance().t2s("財政部稅務入口網統一發票管理"));
	}
	
}
