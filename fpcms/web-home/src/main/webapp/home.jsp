<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${keyword}</title>
	<meta name="keywords" content="${keyword}" />
	<meta name="description" content="${company}代理有限公司于2005年挂牌成立,是经政府批准的具有${city}开发票，代开${city}发票资格的专业税务开票公司,可开${city}材料费发票|住宿费发票|${city}餐饮费发票|${city}酒店发票|广告费发票|等各类发票" />
	<style type="text/css">
		a.subject_title :hover{text-decoration:none;color:#FFF;!important}	
	</style>
</duowan:override>

<duowan:override name="page_left">
<div class="border">
	<div class="subject_bg">
		<div class="subject_title">发票分类</div>
	</div>

	<div>
		<dl class="class_list">
			<dt>
				<a><span>餐饮发票</span></a>
			</dt>
			<dt>
				<a><span>广告费发票</span></a>
			</dt>
			<dt>
				<a><span>建筑发票</span></a>
			</dt>
			<dt>
				<a><span>租赁费发票</span></a>
			</dt>
			<dt>
				<a><span>会议费发票</span></a>
			</dt>
			<dt>
				<a><span>代开其他各类发票</span></a>
			</dt>
		</dl>
	</div>

</div>
<div class="border mt10">
	<div class="subject_bg">
		<div class="subject_title"><a href="${ctx}/channel/show/news.do">热门新闻</a></div>
	</div>
	<div class="m10">
		<dl class="all_list">
			<c:forEach	items="${hot_news}" var="item">
				<dt>
					<a href="${ctx}/content/show/${item.id}.do" style="font-weight: bold;">${item.title}</a>
				</dt>
			</c:forEach>
		</dl>
	</div>
</div>
<div class="border mt10" style="font-size: 15pt">
	<div class="subject_bg">
		<div class="subject_title"><font color="red" style="font-size: 15pt">联系我们</font></div>
	</div>
	<div class="m10 " >
		<div align="left" >
		<b>联系人:</b> ${contactName} <br/>
		<b>电话:</b>&nbsp;&nbsp;&nbsp;${mobile} <br/>
		<b>QQ:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${qq}<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq}&amp;site=${requestHost}&amp;menu=yes"><img src="http://wpa.qq.com/pa?p=2:${qq}:41" border="0" /></a>
		</div>
	</div>
</div>
</duowan:override>

			
<duowan:override name="pageRight">

	<div class="subject_bg">
		<div class="subject_title">${home.channelName}</div>
	</div>
	<div >
		<div class="neirong">
			${home.content}
		</div>
	</div>

	<div class="subject_bg">
		<div class="subject_title">热门新闻</div>
	</div>
	<div>
		<div class="neirong">
			<c:forEach items="${newsPage.itemList}" var="item">
				<h1 class="list_title"><a href="${ctx}/content/show/${item.id}.do" class="news_anchor" title="${item.title}" style="font-weight:bold;">${item.title}</a></h1>
				<div class="list_jj">${ fn:substring(item.content,0,45)}</div>
				<div class="list_other">标签:${item.tags} 作者：<span>${item.author }</span> 日期：<span><fmt:formatDate value="${item.dateCreated }" pattern="yyyy-MM-dd"/></span></div>
			</c:forEach>
		</div>
	</div>
	
	<div class="subject_bg">
		<div class="subject_title" style="">产品中心</div>
	</div>
	<div>
		<div class="neirong">
			<div style="float: left;">
				<img src="${ctx}/images/faipiao/1335531700.jpg" />
				<br />
				<font align="center">
					建筑发票
				</font>
			</div>
			<div style="float: left;">
				<img src="${ctx}/images/faipiao/1335531865.jpg" />
				<br />
				<font align="center">
					广告业发票
				</font>
			</div>
			
			<div  style="float: left;">
				<img src="${ctx}/images/faipiao/1335531855.jpg" />
				<br />
				<font align="center">
					运输业发票
				</font>
			</div>
			<div  style="float: left;">
				<img src="${ctx}/images/faipiao/1335531542.jpg" />
				<br />
				<font align="center">
					商品发票
				</font>
			</div>
			<!-- 
			<div style="float: none;">
				<img src="${ctx}/images/faipiao/1335531696.jpg" />
				<font align="center">
					服务业发票
				</font>
			</div>
			 -->
			
		</div>
	</div>

				
</duowan:override>

<duowan:override name="foot">
		<script type="text/javascript">
			$('.news_anchor').click(function() {
				var url = 'http://www.baidu.com/s?wd='+this.title+" "+'${keyword}';
				window.open(url);
				//location=url;
				return false;
			});
		</script>
</duowan:override>

<duowan:override name="not_exist_foot">

<div>
	<div style="" id="hiddenNews" >
		<h1><a href="${ctx}/channel/show/news.do" style="font-size: 18pt;"><b>热门新闻</b></h1>
		<c:forEach items="${newsPage.itemList}" var="item" varStatus="index">
			<b style="font-size: 15pt;"><a href="${ctx}/content/show/${item.id}.do" title="${item.title}">${index.index+1}:${item.title}</a></b>
		</c:forEach>
	</div>
	<div>
		<script type="text/javascript">
			$(document).ready(function() {
				var hiddenDivFun = function() {
					var offsetTop = $('#hiddenNews').offset().top;
					var offsetLeft = $('#hiddenNews').offset().left;
					var width = $('#hiddenNews').width();
					var height = $('#hiddenNews').height();
					if($.browser.chrome){
						$('#forInsertHiddenDiv').append("<div style='position: absolute; background:#FFF; display: block; ' id='hiddenDiv' ondblclick=\"this.style.display='none'\" ></div>")
						$('#hiddenDiv').offset({ top: offsetTop, left: offsetLeft });
					}else {
						$('#hiddenNews').append("<div style='position: absolute; background:#FFF; display: block; ' id='hiddenDiv' ondblclick=\"this.style.display='none'\" ></div>")
						$('#hiddenDiv').offset({ top: offsetTop, left: offsetLeft });
					}
					$('#hiddenDiv').height(height);
					$('#hiddenDiv').width(width);
				};
				hiddenDivFun();
				//alert(width+" top:"+offsetTop + " hiddenDiv:"+$('#hiddenDiv').offset().top+","+$('#hiddenDiv').offset().left);
			});
		</script>
	</div>
</div>

</duowan:override>


<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<jsp:include page="/layout.do"></jsp:include>