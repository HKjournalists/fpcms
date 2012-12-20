<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Home - Home Page | Architecture - Free Website Template from cssMoban.com</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />

<!-- bootstrap css framework -->
<link href="${ctx}/styles/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctx}/styles/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<script src="${ctx}/styles/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${ctx}/js/topad.js" type="text/javascript"></script>

	<%@ include file="/commons/meta.jsp" %>
	<base href="<%=basePath%>">
	<duowan:block name="head"/>
	
<script  type="text/javascript">
var chatLink = "<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=321172791&amp;site=网站&amp;menu=yes'><img src='images/online_chat.gif'/><br/><img src='images/qq.png'/></a><br/>";
var chatLink2 = "客服1<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=321172791&amp;site=网站&amp;menu=yes'><a href='http://wpa.qq.com/msgrd?v=3&amp;uin=321172791&amp;site=网站&amp;menu=yes'><img src='http://wpa.qq.com/pa?p=2:321172791:41' border='0'/></a><br/>客服2<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=321172791&amp;site=网站&amp;menu=yes'><a href='http://wpa.qq.com/msgrd?v=3&amp;uin=321172791&amp;site=网站&amp;menu=yes'><img src='http://wpa.qq.com/pa?p=2:321172791:41' border='0'/></a><br/>客服3<a href='http://wpa.qq.com/msgrd?v=3&amp;uin=321172791&amp;site=网站&amp;menu=yes'><a href='http://wpa.qq.com/msgrd?v=3&amp;uin=321172791&amp;site=网站&amp;menu=yes'><img src='http://wpa.qq.com/pa?p=2:321172791:41' border='0'/></a><br/>";
generateLeftRightAD(chatLink,chatLink2);
</script>

<style type="text/css">
body { font-family:Verdana; font-size:14px; margin:0;}

#container {margin:0 auto; width:900px;}
#header { height:100px; background:url(images/header-tail.gif); color: #333; margin-bottom:5px;}

#menu { height:64px; background:url(images/header-tail.gif); margin-bottom:5px;}
#menu .nav li {float:left}
#menu .nav li a {float:left;color:#3c3c3c;text-decoration:none;font-size:17px;text-transform:uppercase;font-weight:400}
#menu .nav li a span {float:left;padding:26px 32px 15px 32px}
#menu .nav li a:hover, #header .nav li a.current {background:url(images/nav-left.gif) no-repeat 0 0;color:#d60c0c}
#menu .nav li a:hover span, #header .nav li a.current span {background:url(images/nav-right.gif) no-repeat 100% 0}

.extra-box {background:url(images/header-box.jpg) no-repeat 0 0;width:269px;height:413px;color:#fff}
.extra-box .inner {padding:25px 28px 0 37px}
.extra-box h2 {font-size:24px;line-height:1.2em;text-transform:uppercase;font-weight:600;margin-bottom:25px}
.extra-box h2 span {display:block;font-size:16px;line-height:1.2em}
.extra-box ul {padding-bottom:25px}
.extra-box ul li {background:url(images/arrow1.gif) no-repeat 0 7px;padding:0 0 12px 19px}
.extra-box ul li a {color:#ffd2d2}


#mainContent { height:900px; margin-bottom:5px;}
#sidebar { float:left; width:200px; height:100%; background:#9ff;}
#content { float:right; width:695px; height:100%; background:#cff;}/*因为是固定宽度，采用左右浮动方法可有效避免ie 3像素bug*/
#footer { margin:0 auto; height:60px; background:#6cf; color:#5c5c5c}
</style>

</head>
<body>
<div id="header">This is the Header</div>
<div id="container">
  <div id="menu">
  		<ul class="nav">
			<li class=".current" ><a href="/channel/show/${item.channelCode}.do"><span>中国人民</span></a></li>
			<li><a href="/channel/show/${item.channelCode}.do"><span>中国人民</span></a></li>
			<li><a href="/channel/show/${item.channelCode}.do"><span>中国人民</span></a></li>
			<li><a href="/channel/show/${item.channelCode}.do"><span>中国人民</span></a></li>
		<c:forEach items="${nav}" var="item">
			<li><a href="/channel/show/${item.channelCode}.do"><span>${item.channelName}</span></a></li>
		</c:forEach>
	</ul>
  </div>
  <div id="mainContent">
    <div id="sidebar">
	<div class='extra-box'>
   			<h2>地区发票</h2>
			<ul>
				<c:forEach items="${area}" var="item">
				<li><a href="#">${item.channelName}</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
    <div id="content">
    	<%@ include file="/commons/messages.jsp" %>
		<duowan:block name="content"/>
     </div>
  </div>
</div>
<div id="footer">This is the footer</div>
</body>
</html>