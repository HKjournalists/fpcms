<%@page import="com.fpcms.shared.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsSite 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmssite"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmssite/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="siteDomain" name="siteDomain" value="${cmsSite.siteDomain}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">网站名称</td>	
				<td><c:out value='${cmsSite.siteName}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">网站描述</td>	
				<td><c:out value='${cmsSite.siteDesc}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">网站对应的城市</td>	
				<td><c:out value='${cmsSite.siteCity}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">网站关键词</td>	
				<td><c:out value='${cmsSite.siteKeyword}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">备注</td>	
				<td><c:out value='${cmsSite.remarks}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>