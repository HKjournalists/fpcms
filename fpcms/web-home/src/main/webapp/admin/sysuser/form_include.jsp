<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${sysUser.id}"/>

	<tr>	
		<td class="tdLabel">
			username:
		</td>		
		<td>
		<form:input path="username" id="username" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="username"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			password:
		</td>		
		<td>
		<form:input path="password" id="password" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="password"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			remark:
		</td>		
		<td>
		<form:input path="remark" id="remark" cssClass="" maxlength="255" />
		<font color='red'><form:errors path="remark"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			enabled:
		</td>		
		<td>
		<form:input path="enabled" id="enabled" cssClass="validate-integer max-value-2147483647" maxlength="3" />
		<font color='red'><form:errors path="enabled"/></font>
		</td>
	</tr>	
	
		