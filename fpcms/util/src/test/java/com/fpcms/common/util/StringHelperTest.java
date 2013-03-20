package com.fpcms.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import com.duowan.common.util.DateConvertUtils;

public class StringHelperTest extends Assert{
	
	@Test
	public void test_getMetaDescription() {
		String str = StringHelper.getMetaDescription("<span>&nbsp; &nbsp;${city}${company}于2005年挂牌树立,是经政府同意的首家具有</span><span style=\"color:#E53333;\"></span><span style=\"color:#E53333;\"><strong><u>${city}开发票</u></strong></span><span>，</span><span style=\"background-color:#00D5FF;color:#003399;\"><strong>${city}</strong></span><span style=\"background-color:#00D5FF;color:#003399;\"><strong>代开发票</strong></span><span>资历的专业税务开公司。我司具有一支高素质、经验丰富、训练有素的高效率办税专业财税咨询团队。通过多年来的展开，与工商、税务、财政、海关、银行等部分及各地多家公司树立了紧密的合作关系，在全国各地开设了分公司。公司首要供给</span><span style=\"color:#EE33EE;\"><strong><em>${city}</em></strong></span><span style=\"color:#EE33EE;\"><strong><em>发票</em></strong></span><span>，发票征询，开记账，彻底触及全国事务。</span><br />\r\n<span>${company}</span><span>与全国各地首要城市都有事务协作公司，长时间承受财税署理事务，署理记帐、补帐、旧帐收拾、管帐（财政）征询。署理交税谋划征询，尽可能地获得“节税”的</span><a href=\"http://localhost:8080/fpcms/admin/cmschannel/$%7Bctx%7D/channel/show/news.do\">税收利益</a><span>。</span><br />\r\n<span>&nbsp; &nbsp; &nbsp;</span><span style=\"font-family:宋体;\">本公司本着为贵单位合理合法,节约运营成本和开支的, 在诚信保密的基础上,专业的理财。发票征询办理效劳范围：房屋租赁发票,税务机关发票一式三份,增值税专用发票，商品销售发票,<span style=\"color:#FF9900;\"><strong>${city}餐饮发票</strong></span>,运输发票,</span><span style=\"font-family:宋体;\"><span style=\"color:#FF9900;\"><strong></strong></span><span style=\"color:#EE33EE;\"><u>${city}广告发票</u></span></span><span style=\"font-family:宋体;\">,咨询发票,住宿发票,搬运发票,酒店服务发票,劳务费发票,建筑安装发票,加工修理发票,会议费发票,餐饮定额发票,租赁发票,服务发票 ,工业统一发票,商业统一发票,${city}增值税发票,地方税控发票,等各行业发票等...</span><span style=\"font-family:宋体;\">！</span> \r\n<p>\r\n	&nbsp; &nbsp; &nbsp; 愿同各工商界携手、做交税人兄弟。恪守职业道德，诚挚为广大客户效劳，忠诚实行本身的效劳许诺。咱们拿手为委托人完成最小、合理的交税而进行描绘和运筹，协助客户躲避交税危险，下降公司税负，完成赢利最大化。\r\n</p>\r\n<p>\r\n	<br />\r\n</p>\r\n<div>\r\n	<br />\r\n</div>\r\n<p>\r\n	<br />\r\n</p>");
		assertEquals("${city}${company}于2005年挂牌树立,是经政府同意的首家具有${city}开发票，${city}代开发票资历的专业税务开公司。我司具有一支高素质、经验丰富、训练有素的高效率办税专业财税咨询团队。通过多年来的展开，与工商、税务、财政、海关、银行等部分及各地多家公司树立了紧密的合作关系，在全国各地开设了分公司。公司首要供给${city}发票，发票征询，开记账，彻底触及全国事务。${c",str);
		
		str = StringHelper.getMetaDescription("<span>中国人民银行</span>");
		assertEquals("中国人民银行","中国人民银行");
	}
	
	@Test
	public void test_getYesterdayOuterLinked() {
		Date date = DateUtils.addDays(new Date(),-1);
		String string = StringHelper.getYesterdayOuterLinked("www.163.com");
		assertEquals(string,"http://www.163.com/linked/"+DateConvertUtils.format(date, "yyyyMMdd")+".do");
	}
	
	@Test
	public void test_removeRegexMatch() {
		List<String> list = new ArrayList(Arrays.asList("中国","12月","人民","2013年"));
		StringHelper.removeRegexMatch(list, ".*\\d.*");
		assertEquals(list.toString(),"[中国, 人民]");
	}
	
	@Test
	public void insertAfter() {
		assertEquals("中国,123人民.",StringHelper.insertAfter("中国,人民.", "123", ",","，"));
		
		assertEquals("<a href='http://www.163.com'>java</a>", String.format("<a href='http://%s'>%s</a>","www.163.com",KeywordUtil.getRandomKeyword("java")));
	}
	
}
