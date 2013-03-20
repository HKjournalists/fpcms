<%@page import="com.fpcms.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsContent 维护</title>
	
	<script src="${ctx}/js/rest.js" ></script>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.paginator.page},${page.paginator.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
</duowan:override>

<duowan:override name="content">
	<div class="gridTable">
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  <tr>
				<th style="width:1px;"> </th>
				<th>任务类名</th>
				<th>任务描述</th>
				<th>cron</th>
				<th>最后执行时间</th>
				<th>最后异常</th>
				<th>操作</th>
			  </tr>
		  </thead>
		  <tbody>
		  	  <c:forEach items="${cronJobs}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.paginator.startRow + status.index}</td>
				<td>${item.class.simpleName} </td>
				<td>${item.jobRemark} </td>
				<td>${item.cron} </td>
				<td><fmt:formatDate value="${item.lastExecutedTime}" pattern="MM-dd HH:mm:ss"/></td>
				<td>${item.lastExcetpion}</td>
				<td><a href="${ctx}/admin/cron/exec.do?jobName=${item.class.name}" onclick="if(!confirm('确认执行${item.class.simpleName}:${item.jobRemark}?')) return false; ">手工执行任务</a> </td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
		
	</div>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="../base.jsp" %>

