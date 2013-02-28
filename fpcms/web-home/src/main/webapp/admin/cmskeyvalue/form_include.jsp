<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>


	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>创建时间:
		</td>		
		<td>
		<input value='<fmt:formatDate value="${cmsKeyValue.dateCreated}" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateCreated" name="dateCreated"  maxlength="0" class="required " />
		<font color='red'><form:errors path="dateCreated"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>分组:
		</td>		
		<td>
		<form:input path="keyGroup" id="keyGroup" cssClass="required " maxlength="30" />
		<font color='red'><form:errors path="keyGroup"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>strKey:
		</td>		
		<td>
		<form:input path="strKey" id="strKey" cssClass="required " maxlength="100" />
		<font color='red'><form:errors path="strKey"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			value:
		</td>		
		<td>
		<form:input path="value" id="value" cssClass="" maxlength="500" />
		<font color='red'><form:errors path="value"/></font>
		</td>
	</tr>	
	
		