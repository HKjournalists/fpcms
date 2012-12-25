<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${cmsChannel.channelName} - ${keyword}</title>
</duowan:override>

<duowan:override name="subTitle">
	${cmsChannel.channelName} - ${cmsChannel.channelDesc }
</duowan:override>

<duowan:override name="content">
		<div>
			${cmsChannel.content}
		</div>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>