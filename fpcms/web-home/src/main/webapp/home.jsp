<%@page import="com.fpcms.common.util.Constants"%>
<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${fn:replace(siteName,',','_')}-最值得信赖</title>
	<meta name="keywords" content="${keyword}" />
	<meta name="description" content="${home.metaDescription}" />
	<style type="text/css">
		a.subject_title :hover{text-decoration:none;color:#FFF;!important}	
	</style>
	
	${webMetaVerifyCode}
	
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/application.js"></script>
	<script type="text/javascript" src="${ctx}/js/tmpl.js"></script>
</duowan:override>

<duowan:override name="page_left">
<div class="border">
	<div class="subject_bg">
		<div class="subject_title">发票分类</div>
	</div>

	<div>
		<dl class="class_list">
			<dt>
				<a><span>增值专用发票</span></a>
			</dt>
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
				<a><span>提供其他各类发票</span></a>
			</dt>
		</dl>
	</div>
</div>
 
<div class="border mt10">
	<div class="subject_bg">
		<div class="subject_title"><a href="${ctx}/news.do">随机新闻</a></div>
	</div>
	<div class="m10">
		<dl class="all_list">
			<c:forEach	items="${subSiteNewsList}" var="item" varStatus="status">
				<dt>
					<a href="${item.url}" title="<c:out value='${item.title}'/>" >${status.index+1}. <c:out value='${fn:substring(item.title,0,13)}'/></a>
				</dt>
			</c:forEach>
		</dl>
	</div>
</div>
<div class="border mt10" style="font-size: 15pt">
	<div class="subject_bg">
		<div class="subject_title"><font color="red" style="font-size: 15pt">联系我们</font></div>
	</div>
	<div class="m10 " id="template_home_contact" style="display: none">
		<div align="left" >
		<b>联系人:</b> {%= o.contactName %} <br/>
		<b>电话:</b>&nbsp;&nbsp;&nbsp;{%= o.mobile %} <br/>
		<b>QQ:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{%= o.qq %}<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${qq}&amp;site=${requestHost}&amp;menu=yes"><img src="http://wpa.qq.com/pa?p=2:${qq}:41" border="0" /></a>
		</div>
	</div>
	<script type="text/javascript">
	tmplWithSiteProperty("template_home_contact","${ctx}","${requestHost}");
	</script>
</div>

<div class="border ">
	<div class="subject_bg">
		<div class="subject_title">子站</div>
	</div>
	<div class="m10">
		<dl class="all_list">
			<c:forEach	items="${subSiteList}" var="item">
				<dt>
					<a href="http://${item.siteDomain}${ctx}">${fn:split(item.keyword,",")[0]}</a>
				</dt>
			</c:forEach>
		</dl>
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
			<c:forEach items="${newsPage.itemList}" var="item" varStatus="i">
				<div class="list_title"><a href="${ctx}${item.uri}" class="news_anchor" title="${item.title}" style="font-weight:bold;">${fn:substring(item.title,0,15)}</a></div>
			</c:forEach>
			
			<!-- page slider START -->
			<a href="${ctx}/<%=Constants.CHANNED_CODE_NEWS%>/1.do">第一页</a> | 
			<a href="${ctx}/<%=Constants.CHANNED_CODE_NEWS%>/${newsPage.paginator.prePage}.do">上一页</a> | 
			<c:forEach items="${newsPage.paginator.slider}" var="item">
				<a href="${ctx}/<%=Constants.CHANNED_CODE_NEWS%>/${item}.do">${item}</a> | 
			</c:forEach>
			<a href="${ctx}/<%=Constants.CHANNED_CODE_NEWS%>/${newsPage.paginator.nextPage}.do">下一页</a>
			<a href="${ctx}/<%=Constants.CHANNED_CODE_NEWS%>/${newsPage.paginator.totalPages}.do">最后页</a>
			<!-- page slider END -->
			
		</div>
	</div>
</duowan:override>


<duowan:override name="subPageRight">

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
		</div>
	</div>
				
</duowan:override>

<duowan:override name="foot">
		<script type="text/javascript">
			$('.news_anchor').click(function() {
				return false;
			});
		</script>
		<div class="foot_menu">
		</div>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<jsp:include page="/layout.do"></jsp:include>