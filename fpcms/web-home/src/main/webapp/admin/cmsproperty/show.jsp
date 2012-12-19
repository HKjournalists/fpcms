<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsProperty 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmsproperty"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmsproperty/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="propGroup" name="propGroup" value="${cmsProperty.propGroup}"/>
		<input type="hidden" id="propKey" name="propKey" value="${cmsProperty.propKey}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">propGroup</td>	
				<td><c:out value='${cmsProperty.propGroup}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">propKey</td>	
				<td><c:out value='${cmsProperty.propKey}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">propValue</td>	
				<td><c:out value='${cmsProperty.propValue}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">ramarks</td>	
				<td><c:out value='${cmsProperty.ramarks}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>