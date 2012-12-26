<%@page import="com.fpcms.shared.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>


	<tr>	
		<td class="tdLabel">
			网站域名:
		</td>		
		<td>
		<form:input path="siteDomain" id="siteDomain" cssClass="" maxlength="100" size="100"/>
		<font color='red'><form:errors path="siteDomain"/></font>
		</td>
	</tr>
	
	<tr>	
		<td class="tdLabel">
			网站名称:
		</td>		
		<td>
		<form:input path="siteName" id="siteName" cssClass="" maxlength="100" size="100"/>
		<font color='red'><form:errors path="siteName"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网站描述:
		</td>		
		<td>
		<form:input path="siteDesc" id="siteDesc" cssClass="" maxlength="60" size="100"/>
		<font color='red'><form:errors path="siteDesc"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网站对应的城市:
		</td>		
		<td>
		<form:input path="siteCity" id="siteCity" cssClass="" maxlength="40" size="100"/>
		<font color='red'><form:errors path="siteCity"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网站关键词:
		</td>		
		<td>
		<form:input path="siteKeyword" id="siteKeyword" cssClass="" maxlength="120" size="100"/>
		<font color='red'><form:errors path="siteKeyword"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			备注:
		</td>		
		<td>
		<form:input path="remarks" id="remarks" cssClass="" maxlength="100" size="100"/>
		<font color='red'><form:errors path="remarks"/></font>
		</td>
	</tr>	
	
		