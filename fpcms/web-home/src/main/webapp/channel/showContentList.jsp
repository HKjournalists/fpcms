<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${cmsChannel.channelName}</title>
</duowan:override>

<duowan:override name="subTitle">
	${cmsChannel.channelName}
</duowan:override>

<duowan:override name="content">
		<c:forEach items="${page.itemList}" var="item">
			<h1 class="list_title"><a href="${ctx}/content/show/${item.id}.do" title="${item.title}" style="font-weight:bold;">${item.title}</a></h1>
			<div class="list_jj">${ fn:substring(item.content,0,45)}</div>
			<div class="list_other">标签:${item.tags} 作者：<span>${item.author }</span> 日期：<span><fmt:formatDate value="${item.dateCreated }" pattern="yyyy-MM-dd"/></span></div>
		</c:forEach>
		<a href="${ctx}/channel/showContentList/${cmsChannel.channelCode}.do?page=${page.paginator.prePage}">上一页</a> | 
		<c:forEach items="${page.paginator.slider}" var="item">
			<a href="${ctx}/channel/showContentList/${cmsChannel.channelCode}.do?page=${item}">${item}</a> | 
		</c:forEach>
		<a href="${ctx}/channel/showContentList/${cmsChannel.channelCode}.do?page=${page.paginator.nextPage}">下一页</a>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>