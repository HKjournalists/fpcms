<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	<meta name="robots" content="noarchive">
	
	<duowan:block name="head">
	</duowan:block>
	
	<link href="${ctx}/styles/kaipiao.css" rel="stylesheet" type="text/css" />
</head>

<body>

	<div id="top" class="width">
		<div id="top_logo">
			<div style="width: 600px">
				<div style="height: 100px;">
					<div style="float: left;width: 90px;" ><img src="${ctx}/images/logo_left.jpg" ></img></div>
					<div>
						<div style="height:15px;"></div>
						<div style="float: left;width: 510px;">
							<div><span style="font-size: 30pt;font-weight: 900;">${city}${company}</span></div>
						</div>
						<div  id="template_layout_top_contact" style="float: left;display: block; width: 400px;">
							<font style="font-size: 15pt;font-weight: 900; margin-left: 5px;"><font color=red>QQ</font>:{%= o.qq %} <font color=red>电话</font>:{%= o.mobile %}</font>
						</div>
					</div>
				</div>
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
					<a href="${ctx}/${item.channelCode}.do"><span>${item.channelName}</span></a>
				</li>
				<li class="m"></li>
				</c:forEach>
				
				<li class="r"></li>
			</ul>
		</div>
	</div>
	

	<div class="width mt10">
		<!--Left Over-->
		<div id="page_right" style="float: left;">
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

	<script type="text/javascript" src="${ctx}/js/tmpl.js"></script>
	<script type="text/javascript">
		tmplWithSiteProperty("template_contact_layout","${ctx}","${requestHost}");
		tmplWithSiteProperty("template_layout_top_contact","${ctx}","${requestHost}");
	</script>
		
</body>
</html>
