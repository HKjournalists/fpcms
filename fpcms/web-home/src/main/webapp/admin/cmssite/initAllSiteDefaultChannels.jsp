<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>初始化成功</title>
</duowan:override>

<duowan:override name="content">
	<h1>初始化成功网站列表</h1>
	<table>
		<tr>
			<th>网站域名</th>
			<th>网站名称</th>
			<th>网站描述</th>
			<th>城市</th>
			<th>网站关键词</th>
		</tr>
	<c:forEach var="item" items="${siteList}">
		<tr>
			<td>${item.siteDomain}</td>
			<td><c:out value='${item.siteName}'/>&nbsp;</td>
			<td><c:out value='${item.siteDesc}'/>&nbsp;</td>
			<td><c:out value='${item.city}'/>&nbsp;</td>
			<td><c:out value='${item.keyword}'/>&nbsp;</td>
		</tr>
	</c:forEach>
	</table>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>