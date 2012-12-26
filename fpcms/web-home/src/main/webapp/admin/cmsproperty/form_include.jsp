<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>


	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>propGroup:
		</td>		
		<td>
		<form:input path="propGroup" id="propGroup" cssClass="required " maxlength="255" size="120" />
		<font color='red'><form:errors path="propGroup"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>propKey:
		</td>		
		<td>
		<form:input path="propKey" id="propKey" cssClass="required " maxlength="255"  size="120"/>
		<font color='red'><form:errors path="propKey"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			propValue:
		</td>		
		<td>
		<form:textarea path="propValue" id="propValue" cssClass="" maxlength="255"  size="120" cols="105" rows="20"/>
		<font color='red'><form:errors path="propValue"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			ramarks:
		</td>		
		<td>
		<form:input path="ramarks" id="ramarks" cssClass="" maxlength="255"  size="120"/>
		<font color='red'><form:errors path="ramarks"/></font>
		</td>
	</tr>	
	
		