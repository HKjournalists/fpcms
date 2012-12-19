<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>SysUser 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="sysuser"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/sysuser/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="id" name="id" value="${sysUser.id}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">username</td>	
				<td><c:out value='${sysUser.username}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">password</td>	
				<td><c:out value='${sysUser.password}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">remark</td>	
				<td><c:out value='${sysUser.remark}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">enabled</td>	
				<td><c:out value='${sysUser.enabled}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>