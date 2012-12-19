<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsAttachment 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmsattachment"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmsattachment/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="id" name="id" value="${cmsAttachment.id}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">code</td>	
				<td><c:out value='${cmsAttachment.code}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">attachment</td>	
				<td><c:out value='${cmsAttachment.attachment}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">remarks</td>	
				<td><c:out value='${cmsAttachment.remarks}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">dateLastModified</td>	
				<td><fmt:formatDate value='${cmsAttachment.dateLastModified}' pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>	
				<td class="tdLabel">author</td>	
				<td><c:out value='${cmsAttachment.author}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>