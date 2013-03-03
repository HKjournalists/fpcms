<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsContent 信息</title>
</duowan:override>

<duowan:override name="content">
	<form:form modelAttribute="cmscontent"  >
		<input type="button" value="返回列表" onclick="window.location='${ctx}/admin/cmscontent/index.do'"/>
		<input type="button" value="后退" onclick="history.back();"/>
		
		<input type="hidden" id="id" name="id" value="${cmsContent.id}"/>

		<br />
		<div>	
				<h1>${cmsContent.title}</h1>
				<div>${cmsContent.content}</div>
		</div>
		<br />
			
		<table class="formTable">
			<tr>	
				<td class="tdLabel">频道ID</td>	
				<td><c:out value='${cmsContent.channelCode}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">标签</td>	
				<td><c:out value='${cmsContent.tags}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">网页头title</td>	
				<td><c:out value='${cmsContent.headTitle}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">来源网站</td>	
				<td><a href="http://${cmsContent.sourceSite}" target="_blank">${cmsContent.sourceSite}</a></td>
			</tr>			
			<tr>	
				<td class="tdLabel">文章来源URL</td>	
				<td><a href="${cmsContent.sourceUrl}" target="_blank">${cmsContent.sourceUrl}</a></td>
			</tr>
			<tr>	
				<td class="tdLabel">标题</td>	
				<td><c:out value='${cmsContent.title}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">网站</td>	
				<td><c:out value='${cmsContent.site}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">内容</td>	
				<td><c:out value='${cmsContent.content}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">作者</td>	
				<td><c:out value='${cmsContent.author}'/></td>
			</tr>
			<tr>	
				<td class="tdLabel">创建时间</td>	
				<td><fmt:formatDate value='${cmsContent.dateCreated}' pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>	
				<td class="tdLabel">更新时间</td>	
				<td><fmt:formatDate value='${cmsContent.dateLastModified}' pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>	
				<td class="tdLabel">等级</td>	
				<td><c:out value='${cmsContent.level}'/></td>
			</tr>
		</table>

	</form:form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>