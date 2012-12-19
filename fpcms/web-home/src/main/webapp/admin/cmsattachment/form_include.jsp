<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${cmsAttachment.id}"/>

	<tr>	
		<td class="tdLabel">
			code:
		</td>		
		<td>
		<form:input path="code" id="code" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="code"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			attachment:
		</td>		
		<td>
		<form:input path="attachment" id="attachment" cssClass="" maxlength="255" />
		<font color='red'><form:errors path="attachment"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			remarks:
		</td>		
		<td>
		<form:input path="remarks" id="remarks" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="remarks"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			dateLastModified:
		</td>		
		<td>
		<input value='<fmt:formatDate value="${cmsAttachment.dateLastModified}" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateLastModified" name="dateLastModified"  maxlength="0" class="" />
		<font color='red'><form:errors path="dateLastModified"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			author:
		</td>		
		<td>
		<form:input path="author" id="author" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="author"/></font>
		</td>
	</tr>	
	
		