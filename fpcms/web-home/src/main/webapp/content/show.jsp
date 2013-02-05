<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>${cmsContent.title}</title>
	<meta name="keywords" content="${cmsContent.title}" />
	<meta name="description" content="${cmsContent.metaDescription}" /> 
</duowan:override>

<duowan:override name="content">
		<div>
			<div id="imgContainer"></div>
			<h1>${cmsContent.title}</h1>
			${cmsContent.content}
			<br />
			<span>创建时间:<fmt:formatDate value="${cmsContent.dateCreated}" pattern="yyyy-MM-dd HH:mm"/></span><br />
			<span>本文地址:<a href="${cmsContent.url}">${cmsContent.title}</a><a href="${cmsContent.url}">${cmsContent.url}</a>;</span>
			<div>
			<h3>
			<c:if test="${preCmsContent != null}">上一篇:<a href="${ctx}/content/<fmt:formatDate value="${preCmsContent.dateCreated}" pattern="yyyyMMdd"/>/${preCmsContent.id}.do">${preCmsContent.title}</a><br /></c:if>
			<c:if test="${nextCmsContent != null}">下一篇:<a href="${ctx}/content/<fmt:formatDate value="${nextCmsContent.dateCreated}" pattern="yyyyMMdd"/>/${nextCmsContent.id}.do">${nextCmsContent.title}</a></c:if>
			</h3>
			</div>
		</div>
		
		<% /*
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			var globalImgUrls;
			var request = $.ajax({url:"${ctx}/rpc/ImageWebService/getFemailImageList",dataType:"json"});
			request.done(function(response) { 
		    	//alert("done() "+response.result);
		    	globalImgUrls = response.result;
		    	setTimeout(function() {showImgsFunc(2)},1000);		    	
		    });
			
			var globalLastImgIndex = 0;
			var showImgsFunc = function(showCount) {
				if(globalImgUrls) {
					for(i = 0; i < showCount; i++) {
						if(globalLastImgIndex > globalImgUrls.length) {
							globalLastImgIndex = 0;
						}
			    		var url = globalImgUrls[globalLastImgIndex++];
						$('#imgContainer').append('<img src="${ctx}/proxy/redirect.do?url='+url+'"/>');							
			    	}
				}
			}
			
			$(window).scroll(function () {
				var top = $(document).scrollTop() + $(window).height();
				if(isScrollDown(top)){
					if(top + 900 > $(document).height() ) {
						showImgsFunc(2);
					}
				}
			});
		});
		
		var lastScrollTop = 0;
		function isScrollDown(top) {
			if(lastScrollTop == 0) {
				lastScrollTop = top;
			}
			try {
				if(top > lastScrollTop) {
					return true;
				}else {
					return false;
				}
			}finally {
				lastScrollTop = top;
			}
		}
		</script>
		*/ %>
		
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>

