<%@page import="com.fpcms.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>BlogExternal 维护</title>
	
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
					<td class="tdLabel">博客RPC地址</td>		
					<td>
						<input value="${query.blogRpcUrl}" id="blogRpcUrl" name="blogRpcUrl" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">blogName</td>		
					<td>
						<input value="${query.blogName}" id="blogName" name="blogName" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">tags</td>		
					<td>
						<input value="${query.tags}" id="tags" name="tags" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">categories</td>		
					<td>
						<input value="${query.categories}" id="categories" name="categories" maxlength="100"  class=""/>
					</td>
				</tr>	
				<tr>	
					<td class="tdLabel">博客RPC类型</td>		
					<td>
						<input value="${query.blogRpcApi}" id="blogRpcApi" name="blogRpcApi" maxlength="30"  class=""/>
					</td>
					<td class="tdLabel">博客描述</td>		
					<td>
						<input value="${query.blogDesc}" id="blogDesc" name="blogDesc" maxlength="100"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/blogexternal/index.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="新增" onclick="window.location = '${ctx}/admin/blogexternal/add.do'"/>
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
				<th sortColumn="blog_url" >博客地址</th>
				<th sortColumn="blog_rpc_url" >博客RPC地址</th>
				<th sortColumn="blog_name" >blogName</th>
				<th sortColumn="username" >username</th>
				<th sortColumn="password" >password</th>
				<th sortColumn="tags" >tags</th>
				<th sortColumn="categories" >categories</th>
				<th sortColumn="blog_rpc_api" >博客RPC类型</th>
				<th sortColumn="blog_desc" >博客描述</th>
				<th sortColumn="blog_post_count" >blogPostCount</th>
				<th sortColumn="blog_rpc_api_class" >blogRpcApiClass</th>
				<th sortColumn="enabled" >激活状态</th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><c:out value='${item.blogUrl}'/>&nbsp;</td>
				<td><c:out value='${item.blogRpcUrl}'/>&nbsp;</td>
				<td><c:out value='${item.blogName}'/>&nbsp;</td>
				<td><c:out value='${item.username}'/>&nbsp;</td>
				<td><c:out value='${item.password}'/>&nbsp;</td>
				<td><c:out value='${item.tags}'/>&nbsp;</td>
				<td><c:out value='${item.categories}'/>&nbsp;</td>
				<td><c:out value='${item.blogRpcApi}'/>&nbsp;</td>
				<td><c:out value='${item.blogDesc}'/>&nbsp;</td>
				<td><c:out value='${item.blogPostCount}'/>&nbsp;</td>
				<td><c:out value='${item.blogRpcApiClass}'/>&nbsp;</td>
				<td><c:out value='${item.enabled}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/admin/blogexternal/show.do?blogUrl=${item.blogUrl}&username=${item.username}&password=${item.password}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/blogexternal/edit.do?blogUrl=${item.blogUrl}&username=${item.username}&password=${item.password}">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/blogexternal/delete.do?blogUrl=${item.blogUrl}&username=${item.username}&password=${item.password}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
					<a href="${ctx}/admin/blogexternal/testPostBlog.do?blogUrl=${item.blogUrl}&username=${item.username}&password=${item.password}" >发送测试Blog</a>
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

