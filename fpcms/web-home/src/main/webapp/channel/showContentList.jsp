<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${channel.channelName}</title>
</duowan:override>

<duowan:override name="content">
		<c:forEach items="${cmsContentList}" var="item">
			<h1>创建时间: ${item.dateCreated}</h1>
			${item.content}
			<a href="${ctx}/content/show/${item.id}.do">${item.id}${item.title}</a>
		</c:forEach>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>