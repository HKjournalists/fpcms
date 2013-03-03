<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsDomain 信息</title>
</duowan:override>

<duowan:override name="content">
	<input type="button" value="返回" onclick="history.back();"/>
	<br/>
	<c:forEach items="${cmsDomainList}" var="item">
	www.${item.domain},
	</c:forEach>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>