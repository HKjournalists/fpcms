<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/commons/meta.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>menu</title>

<link href="${ctx}/styles/left.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/js/jquery.js"></script>
<script>
	$(document).ready(function() {
		$('.urbangreymenu li a').click(function() {
			$('.urbangreymenu li a').removeClass('currentClickMenu');
			$(this).addClass('currentClickMenu');
		});
		
		$('.headerbar').click(function() {
			$(this).next().toggle();
		});
	});
</script>


</head>

<body>

<div class="urbangreymenu">
	
	  <h3 class="headerbar">内容管理</h3>
	  <ul>
		<li><a href="${ctx}/admin/cmschannel/index.do" target="rightFrame">频道管理</a></li>
		<li><a href="${ctx}/admin/cmschannel/manageContentList.jsp" target="rightFrame">文章管理</a></li>
		<li><a href="${ctx}/admin/cmscontent/genRandomCmsContent.do?count=10" target="rightFrame">随机生成文章</a></li>
		<li><a href="${ctx}/admin/cmsattachment/index.do" target="rightFrame">附件管理</a></li>
	  </ul>
	  <h3 class="headerbar">系统管理</h3>
	  <ul>
		<li><a href="${ctx}/admin/sysuser/index.do" target="rightFrame">用户管理</a></li>
		<li><a href="${ctx}/admin/cmsproperty/index.do" target="rightFrame">系统属性</a></li>
	  </ul>


</div>

</body>
</html>