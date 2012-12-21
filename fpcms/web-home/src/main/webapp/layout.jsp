<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<title>公司执照-江苏开发票｜</title>
	<meta name="keywords" content="江苏开发票" />
	<meta name="description"
		content="xxxxxxxxxxx代理有限公司于2005年挂牌成立,是经政府批准的首家具有江苏开发票，江苏代开发票，无锡开发票，苏州开发票，常州开发票，无锡代开发票资格的专业税务开票公司" />
	<link href="${ctx}/styles/kaipiao.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="top" class="width">
		<div id="top_logo">QQ:8888888 电话:1866292292</div>
		<div id="top_other">
				<a href="rss" target="_blank">RSS订阅</a> | <a href="publish">匿名投稿</a> |
				<a href="sitemap.html" target="_blank">网站地图</a>
			<dl>
				<dt>
					<input name="key" type="text" class="input" value="请输入关键字" onfocus="if (value =='请输入关键字'){value =''}" onblur="if (value ==''){value='请输入关键字'}" />
				</dt>
				<dt>
					<input type="submit" class="bnt" value="搜 索" />
				</dt>
			</dl>
		</div>
	</div>
	<div id="menu_bg">
		<div id="menu">
			<ul class="menu">
				<li class="l"></li>
				
				<li>
					<a href="/"><span>网站首页</span></a>
				</li>
				<li class="m"></li>
				
				<c:forEach	items="${nav}" var="item">
				<li>
					<a href="${ctx}/channel/show/${item.channelCode}.do"><span>${item.channelName}</span></a>
				</li>
				<li class="m"></li>
				</c:forEach>
				
				<li class="r"></li>
			</ul>
		</div>
	</div>
	
	<div id="banner">
		<img src="${ctx}/images/banner_03.jpg" alt="" />
	</div>

	<div class="width mt10">
		<div id="page_left">
			<duowan:block name="page_left">
			<div class="border">
				<div class="subject_bg">
					<div class="subject_title">地区发票</div>
				</div>

				<div>
					<dl class="class_list">
						<c:forEach	items="${category}" var="item">
							<dt>
								<a href="${ctx}/channel/show/${item.channelCode}.do" id="${item.id == cmsChannel.id ? 'a_hover' : '' }"><span>${item.channelName}</span></a>
							</dt>
						</c:forEach>
					</dl>
				</div>

			</div>
			<div class="border mt10">
				<div class="subject_bg">
					<div class="subject_title">发票分类</div>
				</div>
				<div class="m10">
					<dl class="all_list">
						<c:forEach	items="${category}" var="item">
							<dt>
								<a href="${ctx}/channel/show/${item.channelCode}.do" style="font-weight: bold;">${item.channelName}</a>
							</dt>
						</c:forEach>
					</dl>
				</div>
			</div>
			</duowan:block>
		</div>

		<!--Left Over-->
		<div id="page_right">
			<div class="border">
				<div class="subject_bg">
					<div class="subject_title">title</div>
				</div>
				<div class="m10">
					<div class="other_content">
						<duowan:block name="content"></duowan:block>
					</div>
					<div class="content_page"></div>
				</div>
			</div>

		</div>
	</div>

	<div class="width" id="foot">
		<div class="foot_menu">
			<a href="/">网站首页</a> | <a href="/about">关于我们</a> |
		</div>
		<strong>诚达通财税代理有限公司</strong> <br /> Copyright &copy; 2011-2012嘉华财税代理公司｜&nbsp;&nbsp;保留所有权利<br />
	</div>
	
	<script type="text/javascript" id="bdshare_js" data="type=slide&img=2"></script>
	<script type="text/javascript" id="bdshell_js"></script>
	<script type="text/javascript">
		document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
	</script>

</body>
</html>