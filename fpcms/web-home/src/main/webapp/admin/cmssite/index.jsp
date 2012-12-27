<%@page import="com.fpcms.shared.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsSite 维护</title>
	
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
					
					<td class="tdLabel">网站名称</td>		
					<td>
						<input value="${query.siteName}" id="siteName" name="siteName" maxlength="100"  class=""/>
					</td>
					<td class="tdLabel">网站描述</td>		
					<td>
						<input value="${query.siteDesc}" id="siteDesc" name="siteDesc" maxlength="60"  class=""/>
					</td>
					<td class="tdLabel">网站对应的城市</td>		
					<td>
						<input value="${query.siteCity}" id="siteCity" name="siteCity" maxlength="40"  class=""/>
					</td>
					<td class="tdLabel">网站关键词</td>		
					<td>
						<input value="${query.siteKeyword}" id="siteKeyword" name="siteKeyword" maxlength="120"  class=""/>
					</td>
				</tr>	
				<tr>
					<td class="tdLabel">网站域名</td>		
					<td>
						<input value="${query.siteDomain}" id="siteDomain" name="siteDomain" maxlength="100"  class=""/>
					</td>	
					<td class="tdLabel">备注</td>		
					<td>
						<input value="${query.remarks}" id="remarks" name="remarks" maxlength="100"  class=""/>
					</td>
				</tr>	
			</table>
		</fieldset>
		<div class="handleControl">
			<input type="submit" class="stdButton" style="width:80px" value="查询" onclick="getReferenceForm(this).action='${ctx}/admin/cmssite/index.do'"/>
			<input type="button" class="stdButton" style="width:80px" value="新增" onclick="window.location = '${ctx}/admin/cmssite/add.do'"/>
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
				<th sortColumn="site_domain" >网站域名</th>
				<th sortColumn="site_name" >网站名称</th>
				<th sortColumn="site_desc" >网站描述</th>
				<th sortColumn="site_city" >网站对应的城市</th>
				<th sortColumn="site_keyword" >网站关键词</th>
				<th sortColumn="remarks" >备注</th>
	
				<th>操作</th>
			  </tr>
			  
		  </thead>
		  <tbody>
		  	  <c:forEach items="${page.itemList}" var="item" varStatus="status">
		  	  
			  <tr class="${status.count % 2 == 0 ? 'odd' : 'even'}">
				<td>${page.paginator.startRow + status.index}</td>
				
				<td><a href="${ctx}/admin/cmschannel/index.do?site=${item.siteDomain}" target="_blank">${item.siteDomain}</a>&nbsp;</td>
				<td><c:out value='${item.siteName}'/>&nbsp;</td>
				<td><c:out value='${item.siteDesc}'/>&nbsp;</td>
				<td><c:out value='${item.siteCity}'/>&nbsp;</td>
				<td><c:out value='${item.siteKeyword}'/>&nbsp;</td>
				<td><c:out value='${item.remarks}'/>&nbsp;</td>
				<td>
					<a href="${ctx}/admin/cmssite/show.do?siteDomain=${item.siteDomain}">查看</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmssite/edit.do?siteDomain=${item.siteDomain}">修改</a>&nbsp;&nbsp;
					<a href="${ctx}/admin/cmssite/delete.do?siteDomain=${item.siteDomain}" onclick="doRestDelete(this,'你确认删除?');return false;">删除</a>
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

