<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsSite 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmssite"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmssite/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<h1>关键字</h1>
		<c:forEach items="${cityList}" var="item">
			<p>
				<a href="http://${item.siteDomain};">${prefix}${item.keyword}</a>${item.siteDomain } : 百度记录数${item.recordBaidu } : 关键字等级: ${item.rankBaidu} }
			</p>
		</c:forEach>
		<h1>城市</h1>
		<c:forEach items="${cityList}" var="item">
			<p>
				<a href="http://${item.siteDomain};">${prefix}${item.city}</a>${item.siteDomain }
			</p>
		</c:forEach>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>