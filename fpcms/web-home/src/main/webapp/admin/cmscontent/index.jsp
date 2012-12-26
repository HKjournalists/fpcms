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
	<form id="queryForm" name="queryForm" method="get" style="display: inline;">
	<div class="queryPanel">
		<fieldset>
			<legend>搜索</legend>
			<table>
				<tr>	
					<td class="tdLabel">频道ID</td>		
					<td>
						<input value="${query.channelCode}" id="channelCode" name="channelCode" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">标签</td>		
					<td>
						<input value="${query.tags}" id="tags" name="tags" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">网页头title</td>		
					<td>
						<input value="${query.headTitle}" id="headTitle" name="headTitle" maxlength="200"  class=""/>
					</td>
					<td class="tdLabel">标题</td>		
					<td>
						<input value="${query.title}" id="title" name="title" maxlength="200"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">内容</td>		
					<td>
						<input value="${query.content}" id="content" name="content" maxlength="65535"  class=""/>
					</td>
					<td class="tdLabel">作者</td>		
					<td>
						<input value="${query.author}" id="author" name="author" maxlength="50"  class=""/>
					</td>
					<td class="tdLabel">创建时间</td>		
					<td>
						<input value="<fmt:formatDate value='${query.dateCreatedBegin}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateCreatedBegin" name="dateCreatedBegin"   />
						<input value="<fmt:formatDate value='${query.dateCreatedEnd}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateCreatedEnd" name="dateCreatedEnd"   />
					</td>
					<td class="tdLabel">更新时间</td>		
					<td>
						<input value="<fmt:formatDate value='${query.dateLastModifiedBegin}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateLastModifiedBegin" name="dateLastModifiedBegin"   />
						<input value="<fmt:formatDate value='${query.dateLastModifiedEnd}' pattern='yyyy-MM-dd'/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateLastModifiedEnd" name="dateLastModifiedEnd"   />
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">网站</td>		
					<td>
						<input value="${query.site}" id="site" name="site" maxlength="50"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/cmscontent/index.do'"/>
			<input type="submit" class="stdButton" style="width:80px" value="新增" onclick="getReferenceForm(this).action = '${ctx}/admin/cmscontent/add.do'"/>
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
				<th sortColumn="title" >ID</th>
				<th sortColumn="title" >标题</th>
				<th sortColumn="author" >作者</th>
				<th sortColumn="date_last_modified" >更新时间</th>
	 
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><c:out value='${item.id}'/>&nbsp;</td>
				<td><c:out value='${item.title}'/>&nbsp;</td>
				<td><c:out value='${item.author}'/>&nbsp;</td>
				<td><fmt:formatDate value='${item.dateLastModified}' pattern='yyyy-MM-dd'/>&nbsp;</td>
				<td>
					<a href="${ctx}/admin/cmscontent/show.do?id=${item.id}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmscontent/edit.do?id=${item.id}">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmscontent/delete.do?id=${item.id}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
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

