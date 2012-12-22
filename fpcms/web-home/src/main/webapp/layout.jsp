<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<duowan:block name="head">
	<title>公司执照-${default.keyword}</title>
	<meta name="keywords" content="${default.keyword}" />
	<meta name="description" content="${default.company}代理有限公司于2005年挂牌成立,是经政府批准的具有${default.city}开发票，代开发票资格的专业税务开票公司" />
	</duowan:block>
	<script type="text/javascript" src="${ctx}/js/application.js"></script>
	<script type="text/javascript" src="${ctx}/js/topad.js"></script>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<link href="${ctx}/styles/kaipiao.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="top" class="width">
		<div>
		<div id="top_logo">
			<div style="width: 600px">
				<div style="height: 100px;">
					<div style="float: left;width: 90px;" ><img src="${ctx}/images/logo_left.jpg"></img></div>
					<div>
						<div style="height:15px;"></div>
						<div style="float: left;width: 510px;">
							<div><span style="font-size: 30pt;font-weight: 900;">${default.company}</span></div>
						</div>
						<div  style="float: left;display: block; width: 400px;">
							<font style="font-size: 15pt;font-weight: 900; margin-left: 5px;"><font color=red>QQ</font>:${default.qq} <font color=red>电话</font>:${default.mobile }</font>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="top_other">
			<a href="javascript:addFavorite('${default.company}-代开发票');">加入收藏夹</a>
			<dl>
				<dt>
					<input id="searchKeyword" name="searchKeyword" type="text" class="input" value="请输入关键字" onfocus="if (value =='请输入关键字'){value =''}" onblur="if (value ==''){value='请输入关键字'}" />
				</dt>
				<dt>
					<input type="button" class="bnt" value="搜 索" onclick="location='http://www.baidu.com/s?wd=domain:'+location.host+' '+document.getElementById('searchKeyword').value" />
				</dt>
			</dl>
		</div>
		</div>
	</div>
	<div id="menu_bg">
		<div id="menu">
			<ul class="menu">
				<li class="l"></li>
				
				<li>
					<a href="${ctx}/"><span>网站首页</span></a>
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
					<div class="subject_title">发票分类</div>
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
					<div class="subject_title">热门新闻</div>
				</div>
				<div class="m10">
					<dl class="all_list">
						<c:forEach	items="${area}" var="item">
							<dt>
								<a href="${ctx}/channel/show/${item.channelCode}.do" style="font-weight: bold;">${item.channelName}</a>
							</dt>
						</c:forEach>
					</dl>
				</div>
			</div>
			<div class="border mt10" style="font-size: 15pt">
				<div class="subject_bg">
					<div class="subject_title"><font color="red" style="font-size: 15pt">联系我们</font></div>
				</div>
				<div class="m10 " >
					<div align="left" >
					<b>联系人:</b> ${default.contactName} <br/>
					<b>电话:</b>&nbsp;&nbsp;&nbsp;${default.mobile} <br/>
					<b>QQ:</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${default.qq}<a href="http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=${requestHost}&amp;menu=yes"><img src="http://wpa.qq.com/pa?p=2:${default.qq}:41" border="0" /></a>
					</div>
				</div>
			</div>
			</duowan:block>
		</div>

		<!--Left Over-->
		<div id="page_right">
			<duowan:block name="pageRight">
			<div class="border">
				<div class="subject_bg">
					<div class="subject_title"><duowan:block name="subTitle"></duowan:block></div>
				</div>
				<div >
					<div class="neirong">
						<duowan:block name="content"></duowan:block>
					</div>
				</div>
			</div>
			</duowan:block>
		</div>
	</div>

	<div class="width" id="foot">
		<div class="foot_menu">
			<a href="/">网站首页</a> | 
		</div>
		<strong>${default.company}</strong> <br /> Copyright &copy; 2005-2012｜&nbsp;&nbsp;保留所有权利<br />
		<duowan:block name="foot">
		</duowan:block>
	</div>
	
	<script type="text/javascript" id="bdshare_js" data="type=slide&img=2"></script>
	<script type="text/javascript" id="bdshell_js"></script>
	<script type="text/javascript">
		document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + new Date().getHours();
	</script>
	<script  type="text/javascript">
		var chatLink = "<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=${requestHost}&amp;menu=yes'><img src='${ctx}/images/online_chat.gif'/><br/><img src='${ctx}/images/qq.png'/></a><br/>";
		var chatLink2 = "客服1<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=${requestHost}&amp;menu=yes'><a href='http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=网站&amp;menu=yes'><img src='http://wpa.qq.com/pa?p=2:${default.qq}:41' border='0'/></a><br/>客服2<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=网站&amp;menu=yes'><a href='http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=网站&amp;menu=yes'><img src='http://wpa.qq.com/pa?p=2:${default.qq}:41' border='0'/></a><br/>客服3<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=网站&amp;menu=yes'><a href='http://wpa.qq.com/msgrd?v=3&amp;uin=${default.qq}&amp;site=网站&amp;menu=yes'><img src='http://wpa.qq.com/pa?p=2:${default.qq}:41' border='0'/></a><br/>";
		generateLeftRightAD(chatLink,chatLink2);
	</script>
</body>
</html>