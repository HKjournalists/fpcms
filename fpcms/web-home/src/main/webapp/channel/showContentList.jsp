<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${cmsChannel.channelName} - 第${page.paginator.page}页 - ${keyword} </title>
</duowan:override>

<duowan:override name="subTitle">
	${cmsChannel.channelName}
</duowan:override>

<duowan:override name="content">
		<c:forEach items="${page.itemList}" var="item">
			<div class="list_title"><a href="${ctx}/content/<fmt:formatDate value="${item.dateCreated}" pattern="yyyyMMdd"/>/${item.id}.do" title="${item.title}" style="font-weight:bold;">${item.title}</a></div>
			<div class="list_jj">${ fn:substring(item.metaDescription,50,100)}</div>
			<div class="list_other">标签:${item.tags} 日期：<span><fmt:formatDate value="${item.dateCreated }" pattern="yyyy-MM-dd HH:00:00"/></span></div>
		</c:forEach>
		
		<!-- page slider START -->
		<a href="${ctx}/${cmsChannel.channelCode}/1.do">第一页</a> | 
		<a href="${ctx}/${cmsChannel.channelCode}/${page.paginator.prePage}.do">上一页</a> | 
		<c:forEach items="${page.paginator.slider}" var="item">
			<a href="${ctx}/${cmsChannel.channelCode}/${item}.do">${item}</a> | 
		</c:forEach>
		<a href="${ctx}/${cmsChannel.channelCode}/${page.paginator.nextPage}.do">下一页</a>
		<a href="${ctx}/${cmsChannel.channelCode}/${page.paginator.totalPages}.do">最后页</a>
		<!-- page slider END -->
		
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>