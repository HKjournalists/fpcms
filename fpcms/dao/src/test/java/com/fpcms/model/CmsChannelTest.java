package com.fpcms.model;

import org.junit.Assert;
import org.junit.Test;


public class CmsChannelTest extends Assert{

	@Test
	public void test() {
		assertFalse(CmsChannel.HOME.getContent().isEmpty());
		for(CmsChannel item : CmsChannel.NAV_SUB_CHANNELS) {
			assertFalse(item.getContent().isEmpty());
		}
	}
	
}
