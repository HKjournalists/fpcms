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
		<script type="text/javascript" src="${ctx}/js/application.js"></script>
		<script type="text/javascript" src="${ctx}/js/topad.js"></script>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="${ctx}/js/tmpl.js"></script>
	
		<div id="template_channel_content">
			${cmsChannel.content}
		</div>
		
		<script type="text/javascript">
		tmplWithSiteProperty("template_channel_content","${ctx}","${requestHost}");
		</script>
	
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>