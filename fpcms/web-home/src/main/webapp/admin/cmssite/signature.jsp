<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsDomain 信息</title>
</duowan:override>

<duowan:override name="content">
	<input type="button" value="返回" onclick="history.back();"/>
	<br/>
	
	<c:forEach items="${cmsSiteList}" var="site"><c:if test="${fn:startsWith(site.siteDomain,'www.')}"><a href="http://${site.siteDomain}">${site.keywordList[0]}</a>${site.siteDomain},</c:if></c:forEach>
	
	<hr />
	<hr />
	<c:forEach items="${cmsSiteList}" var="site"><c:if test="${fn:startsWith(site.siteDomain,'www.')}"><a href="http://${site.siteDomain}">${site.city}</a>${site.siteDomain},</c:if></c:forEach>
	<hr />
	<hr />
	
	<c:forEach items="${cmsSiteList}" var="site">
	<c:if test="${fn:startsWith(site.siteDomain,'www.')}">
		<c:forEach items="${site.keywordList}" var="keyword"><a href="http://${site.siteDomain}">${keyword}</a>${site.siteDomain},</c:forEach>
		<br></br>
	</c:if>
	</c:forEach>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>