<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsChannel 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmschannel"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmschannel/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="id" name="id" value="${cmsChannel.id}"/>
	
		<table class="formTable">
			<tr>	
				<td class="tdLabel">频道名称</td>	
				<td><c:out value='${cmsChannel.channelName}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">频道代码</td>	
				<td><c:out value='${cmsChannel.channelCode}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">频道描述</td>	
				<td><c:out value='${cmsChannel.channelDesc}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">父亲ID</td>	
				<td><c:out value='${cmsChannel.parentId}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">更新时间</td>	
				<td><fmt:formatDate value='${cmsChannel.dateLastModified}' pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>	
				<td class="tdLabel">作者</td>	
				<td><c:out value='${cmsChannel.author}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">网站</td>	
				<td><c:out value='${cmsChannel.site}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">等级</td>	
				<td><c:out value='${cmsChannel.level}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">内容</td>	
				<td><c:out value='${cmsChannel.content}'/></td>
			</tr>
		</table>
	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>