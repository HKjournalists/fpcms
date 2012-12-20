<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${cmsContent.title}</title>
</duowan:override>

<duowan:override name="content">
		<h1>${cmsContent.title} 更新时间:${cmsContent.dateLastModified} 创建时间:${cmsContent.dateCreated}</h1>
		<div>
			${cmsContent.content}
		</div>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>