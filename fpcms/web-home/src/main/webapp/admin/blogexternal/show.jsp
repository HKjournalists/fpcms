<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>BlogExternal 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="blogexternal"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/blogexternal/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="blogUrl" name="blogUrl" value="${blogExternal.blogUrl}"/>
		<input type="hidden" id="username" name="username" value="${blogExternal.username}"/>
		<input type="hidden" id="password" name="password" value="${blogExternal.password}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">博客地址</td>	
				<td><c:out value='${blogExternal.blogUrl}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">博客RPC地址</td>	
				<td><c:out value='${blogExternal.blogRpcUrl}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">blogName</td>	
				<td><c:out value='${blogExternal.blogName}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">username</td>	
				<td><c:out value='${blogExternal.username}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">password</td>	
				<td><c:out value='${blogExternal.password}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">tags</td>	
				<td><c:out value='${blogExternal.tags}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">categories</td>	
				<td><c:out value='${blogExternal.categories}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">博客RPC类型</td>	
				<td><c:out value='${blogExternal.blogRpcApi}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">博客描述</td>	
				<td><c:out value='${blogExternal.blogDesc}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>