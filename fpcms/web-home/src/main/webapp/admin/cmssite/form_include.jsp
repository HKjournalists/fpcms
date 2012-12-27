<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>


	<tr>	
		<td class="tdLabel">
			网站域名:
		</td>		
		<td>
		<form:input path="siteDomain" id="siteDomain" cssClass="" maxlength="100" />
		<font color='red'><form:errors path="siteDomain"/></font>
		</td>
	</tr>
	
	<tr>	
		<td class="tdLabel">
			网站名称:
		</td>		
		<td>
		<form:input path="siteName" id="siteName" cssClass="" maxlength="100" />
		<font color='red'><form:errors path="siteName"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网站描述:
		</td>		
		<td>
		<form:input path="siteDesc" id="siteDesc" cssClass="" maxlength="60" />
		<font color='red'><form:errors path="siteDesc"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网站对应的城市:
		</td>		
		<td>
		<form:input path="city" id="city" cssClass="" maxlength="40" />
		<font color='red'><form:errors path="city"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网站关键词:
		</td>		
		<td>
		<form:input path="keyword" id="keyword" cssClass="" maxlength="120" />
		<font color='red'><form:errors path="keyword"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			备注:
		</td>		
		<td>
		<form:input path="remarks" id="remarks" cssClass="" maxlength="100" />
		<font color='red'><form:errors path="remarks"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			公司:
		</td>		
		<td>
		<form:input path="company" id="company" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="company"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			联系人:
		</td>		
		<td>
		<form:input path="contactName" id="contactName" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="contactName"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			移动电话:
		</td>		
		<td>
		<form:input path="mobile" id="mobile" cssClass="" maxlength="20" />
		<font color='red'><form:errors path="mobile"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			QQ:
		</td>		
		<td>
		<form:input path="qq" id="qq" cssClass="" maxlength="20" />
		<font color='red'><form:errors path="qq"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			邮件:
		</td>		
		<td>
		<form:input path="email" id="email" cssClass="validate-email " maxlength="20" />
		<font color='red'><form:errors path="email"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			作者:
		</td>		
		<td>
		<form:input path="author" id="author"  maxlength="20" />
		<font color='red'><form:errors path="author"/></font>
		</td>
	</tr>		
		