<%@page import="com.fpcms.common.util.Constants"%>
<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<c:forEach items="${list}" var="item">
	<h3>${item}</h3>
</c:forEach>

