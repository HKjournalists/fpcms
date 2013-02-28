<%@page import="com.fpcms.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsKeyValue 维护</title>
	
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
	<form id="queryForm" name="queryForm" method="get" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel">strKey</td>		
					<td>
						<input value="${query.strKey}" id="strKey" name="strKey" maxlength="500"  class=""/>
					</td>
					<td class="tdLabel">keyGroup</td>		
					<td>
						<input value="${query.keyGroup}" id="keyGroup" name="keyGroup" maxlength="500"  class=""/>
					</td>
					
					<td class="tdLabel">创建时间</td>		
					<td>
						<input value="<fmt:formatDate value='${query.dateCreatedBegin}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateCreatedBegin" name="dateCreatedBegin"   />
						<input value="<fmt:formatDate value='${query.dateCreatedEnd}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateCreatedEnd" name="dateCreatedEnd"   />
					</td>
					<td class="tdLabel">value</td>		
					<td>
						<input value="${query.value}" id="value" name="value" maxlength="500"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/cmskeyvalue/index.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="新增" onclick="window.location = '${ctx}/admin/cmskeyvalue/add.do'"/>
		<div>
	
	</div>
	
	<div class="gridTable">
	
		<simpletable:pageToolbar paginator="${page.paginator}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
	
		<table width="100%"  border="0" cellspacing="0" class="gridBody">
		  <thead>
			  
			  <tr>
				<th style="width:1px;"> </th>
				
				<!-- 排序时为th增加sortColumn即可,new SimpleTable('sortColumns')会为tableHeader自动增加排序功能; -->
				<th sortColumn="date_created" >创建时间</th>
				<th sortColumn="key_group" >分组</th>
				<th sortColumn="str_key" >strKey</th>
				<th sortColumn="value" >value</th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><fmt:formatDate value='${item.dateCreated}' pattern='yyyy-MM-dd'/>&nbsp;</td>
				<td><c:out value='${item.keyGroup}'/>&nbsp;</td>
				<td><c:out value='${item.strKey}'/>&nbsp;</td>
				<td><c:out value='${item.value}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/admin/cmskeyvalue/show.do?keyGroup=${item.keyGroup}&strKey=${item.strKey}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmskeyvalue/edit.do?keyGroup=${item.keyGroup}&strKey=${item.strKey}">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmskeyvalue/delete.do?keyGroup=${item.keyGroup}&strKey=${item.strKey}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
				</td>
			  </tr>
			  
		  	  </c:forEach>
		  </tbody>
		</table>
	
		<simpletable:pageToolbar paginator="${page.paginator}">
		显示在这里是为了提示你如何自定义表头,可修改模板删除此行
		</simpletable:pageToolbar>
		
	</div>
	</form>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

