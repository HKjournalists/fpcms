<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${cmsContent.title}</title>
	<meta name="keywords" content="${cmsContent.title}" />
	<meta name="description" content="${fn:substring(item.content,0,80)}" /> 
</duowan:override>

<duowan:override name="content">
		<div>
			
<%-- 			<iframe src="${ctx}/proxy/redirect.do?url=http://www.22mm.cc/mm/qingliang/PHaPabJJmaCPPmH.html" width="100%" height="1500px"></iframe> --%>
			<h1>${cmsContent.title}</h1>
			${cmsContent.content}
			<br />
			<span>创建时间:<fmt:formatDate value="${cmsContent.dateCreated}" pattern="yyyy-MM-dd"/></span>
			<div><h3>上一篇:<a href="${ctx}/content/show/${preCmsContent.id}.do">${preCmsContent.title}</a><br />下一篇:<a href="${ctx}/content/show/${nextCmsContent.id}.do">${nextCmsContent.title}</a></h3></div>
			<div id="imgContainer"></div>
		</div>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script type="text/javascript">
		
		$(window).scroll(function () {
			var top = $(document).scrollTop() + $(window).height();
			if(isScrollDown(top)){
				if(top + 600 > $(document).height()) {
					$('#imgContainer').append('<img src="${ctx}/proxy/redirect.do?url=http://qlimg1.meimei22.com/pic/qingliang/2013-1-9/1/1-121210101412-50.jpg"/>');
					$('#imgContainer').append('<img src="${ctx}/proxy/redirect.do?url=http://qlimg1.meimei22.com/pic/qingliang/2013-1-9/1/1-121210101412.jpg"/>');
					$('#imgContainer').append('<img src="${ctx}/proxy/redirect.do?url=http://qlimg1.meimei22.com/pic/qingliang/2013-1-9/1/1-121210101413.jpg"/>');
					$('#imgContainer').append('<img src="${ctx}/proxy/redirect.do?url=http://qlimg1.meimei22.com/pic/qingliang/2013-1-9/1/1-121210101414.jpg"/>');
				}else {
					//alert(top+"   "+$(document).height())
				}
			}
		});
		
		var lastScrollTop = 0;
		function isScrollDown(top) {
			if(lastScrollTop == 0) {
				lastScrollTop = top;
			}
			try {
				if(top > lastScrollTop) {
					return true;
				}else {
					return false;
				}
			}finally {
				lastScrollTop = top;
			}
		}
		</script>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

