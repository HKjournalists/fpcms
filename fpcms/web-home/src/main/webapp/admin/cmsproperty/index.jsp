<%@page import="com.fpcms.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsProperty 维护</title>
	
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
					<td class="tdLabel">propGroup</td>		
					<td>
						<input value="${query.propGroup}" id="propGroup" name="propGroup" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel">propKey</td>		
					<td>
						<input value="${query.propKey}" id="propKey" name="propKey" maxlength="255"  class=""/>
					</td>		
					<td class="tdLabel">propValue</td>		
					<td>
						<input value="${query.propValue}" id="propValue" name="propValue" maxlength="255"  class=""/>
					</td>
					<td class="tdLabel">ramarks</td>		
					<td>
						<input value="${query.ramarks}" id="ramarks" name="ramarks" maxlength="255"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/cmsproperty/index.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action='${ctx}/admin/cmsproperty/add.do'"/>
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
				<th sortColumn="prop_group" >propGroup</th>
				<th sortColumn="prop_key" >propKey</th>
				<th sortColumn="prop_value" >propValue</th>
				<th sortColumn="ramarks" >ramarks</th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><c:out value='${item.propGroup}'/>&nbsp;</td>
				<td><c:out value='${item.propKey}'/>&nbsp;</td>
				<td><c:out value='${item.propValue}'/>&nbsp;</td>
				<td><c:out value='${item.ramarks}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/admin/cmsproperty/show.do?propGroup=${item.propGroup}&propKey=${item.propKey}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmsproperty/edit.do?propGroup=${item.propGroup}&propKey=${item.propKey}">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmsproperty/delete.do?propGroup=${item.propGroup}&propKey=${item.propKey}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
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

