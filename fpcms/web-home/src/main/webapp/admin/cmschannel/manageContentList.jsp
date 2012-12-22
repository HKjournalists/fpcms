<%@page import="com.fpcms.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable"%>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsChannel 维护</title>
	
	<script src="${ctx}/js/rest.js" ></script>
	<link href="<c:url value="/widgets/simpletable/simpletable.css"/>" type="text/css" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/widgets/simpletable/simpletable.js"/>"></script>
	
	<link rel="STYLESHEET" type="text/css" href="${ctx}/js/dhtmlXTree/dhtmlxtree.css">
	<script  src="${ctx}/js/dhtmlXTree/dhtmlxcommon.js"></script>
	<script  src="${ctx}/js/dhtmlXTree/dhtmlxtree.js"></script>

	<script type="text/javascript" >
		$(document).ready(function() {
			// 分页需要依赖的初始化动作
			window.simpleTable = new SimpleTable('queryForm',${page.paginator.page},${page.paginator.pageSize},'${pageRequest.sortColumns}');
		});
	</script>
	
	<style type="text/css">
	#container {margin:0 auto; width:100%;}
	#sidebar { float:left; width:20%; height:900px; background:#6cf;}
	#detail { float:right; width:80%; height:900px; background:#cff;}
	</style>
				
</duowan:override>

<duowan:override name="content">
	<div id="container">
		<div style="display: block;">
			<div id="sidebar">
				<div>
					<input type="button" onclick="tree.openAllItems(0)"  value="打开所有" ></button>
					<input type="button" onclick="tree.closeAllItems()" value="关闭所有"></button>
				</div>
				<div id="dhtmlXTree" style="width: 100%; height: 100%; background-color: #f5f5f5; border: 1px solid Silver;">
				</div>
			</div>
			<div id="detail">
				<iframe id="channelContentIframe" src="${ctx}/admin/cmscontent/index.do?channelId=${channelId}" style="width: 100%; height: 100%;"></iframe>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		// API reference: http://docs.dhtmlx.com/doku.php?id=dhtmlxtree:toc
				
		var tree = new dhtmlXTreeObject("dhtmlXTree", "100%","100%", 0);
		tree.setSkin('csh_vista');
		tree.setImagePath("${ctx}/js/dhtmlXTree/imgs/csh_dhx_skyblue/");
		tree.enableDragAndDrop(false);
		tree.enableTreeLines(true);
		tree.enableHighlighting(true);
		//tree.setImageArrays("plus", "", "", "", "plus.gif");
		//tree.setImageArrays("minus", "", "", "", "minus.gif");
		//tree.setStdImages("book.gif", "books_open.gif", "books_close.gif");
		//tree.setXMLAutoLoading("../common/tree4.xml");
		tree.loadXML("${ctx}/admin/cmschannel/treeXml.do",function() {
			clickTree($.cookie('currentTreeId'));
		});
	
		var clickTree = function(id) {
			if(id == null) return;
			
			var channelCode = tree.getUserData(id, "channelCode");
			var site = tree.getUserData(id, "site");
			//document.getElementById('detailframe').src = '${ctx}/admin/cmschannel/edit.do?id='+id;
			document.getElementById('channelContentIframe').src = '${ctx}/admin/cmscontent/index.do?channelCode='+channelCode+'&site='+site;
			
			$.cookie('currentTreeId',id, { expires: 1, });
			
			tree.selectItem(id);			
			tree.focusItem(id);
			tree.openAllItems(id);
			return true;
		};
		tree.attachEvent("onClick", clickTree);
		
	</script>
			
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

