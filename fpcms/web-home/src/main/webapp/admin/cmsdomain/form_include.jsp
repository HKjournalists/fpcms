<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<tr>	
		<td class="tdLabel">
			域名
		</td>		
		<td>
		<form:input path="domain" id="domain" cssClass="" maxlength="200" />
		<font color='red'><form:errors path="domain"/></font>
		</td>
	</tr>
	
	<tr>	
		<td class="tdLabel">
			备注:
		</td>		
		<td>
		<form:input path="remarks" id="remarks" cssClass="" maxlength="200" />
		<font color='red'><form:errors path="remarks"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			IP地址:
		</td>		
		<td>
		<form:input path="ip" id="ip" cssClass="" maxlength="200" />
		<font color='red'><form:errors path="ip"/></font>
		</td>
	</tr>	
	
	<tr>
		<td class="tdLabel">
			扩展key_value属性:
		</td>		
		<td>
		<form:textarea path="props" id="props" cssClass="" maxlength="2000" cols="80" rows="30" />
		<font color='red'><form:errors path="props"/></font>
		</td>
	</tr>			