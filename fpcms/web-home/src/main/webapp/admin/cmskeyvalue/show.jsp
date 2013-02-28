<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsKeyValue 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmskeyvalue"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmskeyvalue/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="keyGroup" name="keyGroup" value="${cmsKeyValue.keyGroup}"/>
		<input type="hidden" id="strKey" name="strKey" value="${cmsKeyValue.strKey}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">创建时间</td>	
				<td><fmt:formatDate value='${cmsKeyValue.dateCreated}' pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>	
				<td class="tdLabel">分组</td>	
				<td><c:out value='${cmsKeyValue.keyGroup}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">strKey</td>	
				<td><c:out value='${cmsKeyValue.strKey}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">value</td>	
				<td><c:out value='${cmsKeyValue.value}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>