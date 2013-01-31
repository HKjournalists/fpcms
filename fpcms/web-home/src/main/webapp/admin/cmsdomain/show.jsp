<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsDomain 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmsdomain"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmsdomain/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="domain" name="domain" value="${cmsDomain.domain}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">备注</td>	
				<td><c:out value='${cmsDomain.remarks}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">扩展key_value属性</td>	
				<td><c:out value='${cmsDomain.props}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">IP地址</td>	
				<td><c:out value='${cmsDomain.ip}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>