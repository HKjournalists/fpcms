<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>批量更新</title>
</duowan:override>

<duowan:override name="content">
	<form:form method="post" action="${ctx}/admin/cmssite/batchUpdateProperty.do" modelAttribute="cmsSite">
		<input id="submitButton" name="submitButton" type="submit" value="提交" />
		<input type="button" value="后退" onclick="history.back();"/>
		<div>
		选择的站点
		<c:forEach items="${paramValues.sites}" var="item">
		<input name="sites" value="${item}" type="hidden" />
		${item}
		</c:forEach>
		</div>
		
		<table class="formTable">
			<tr>
				<td>Key</td>
				<td>
					<c:forEach items="${CmsSitePropertyEnum}" var="item">
					${item.desc}<input name="key" value="${item.code}" type="radio" /> <br />
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td>Value</td><td><textarea cols="40" rows="10" name="value">${value}</textarea> </td>
			</tr>
		</table>
		
	</form:form>
	
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>