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
		
		<input type="hidden" id="siteDomain" name="siteDomain" value="${cmsSite.siteDomain}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">网站域名</td>	
				<td><c:out value='${cmsSite.siteDomain}'/></td>
			</tr>
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
				<td><c:out value='${cmsSite.city}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">网站关键词</td>	
				<td><c:out value='${cmsSite.keyword}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">备注</td>	
				<td><c:out value='${cmsSite.remarks}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">公司</td>	
				<td><c:out value='${cmsSite.company}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">联系人</td>	
				<td><c:out value='${cmsSite.contactName}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">移动电话</td>	
				<td><c:out value='${cmsSite.mobile}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">QQ</td>	
				<td><c:out value='${cmsSite.qq}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">邮件</td>	
				<td><c:out value='${cmsSite.email}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>