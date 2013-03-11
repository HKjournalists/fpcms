<%@page import="com.fpcms.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsContent 维护</title>
	
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
</duowan:override>

<duowan:override name="content">
	
	<div style="font-size: 12px;">
		<h1>带网站site</h1>
		<table>
			<thead>
			<tr>
				<th>网站</th>
				<th>日期</th>
				<th>新文章数量</th>
			</tr>
			</thead>
			
			<tbody>
	  	  	<c:forEach items="${list}" var="item" varStatus="status">
	  	  	<tr>
	  	  		<td>${item.site}</td>
	  	  		<td>${item.day}</td>
	  	  		<td>${item.new_content_count}</td>
	  	  	</tr>
	  	 	</c:forEach>		
			</tbody>
			
		</table>
	</div>
	
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

