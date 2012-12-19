<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<duowan:override name="head">
	<title>CmsChannel编辑</title>
</duowan:override>

<duowan:override name="content">
	<form:form method="put" action="${ctx}/admin/cmschannel/update.do" modelAttribute="cmsChannel">
		<input type="button" value="添加子节点" onclick="window.location='${ctx}/admin/cmschannel/add.do?parentId=${cmsChannel.id}' " />
		<input type="button" value="删除" onclick="window.location='${ctx}/admin/cmschannel/delete.do?id=${cmsChannel.id}' " />
		<input id="submitButton" name="submitButton" type="submit" value="保存" />
		
		<table class="formTable">
		<%@ include file="form_include.jsp" %>
		</table>
	</form:form>
	
	<script>
		
		new Validation(document.forms[0],{onSubmit:true,onFormValidate : function(result,form) {
			var finalResult = result;
			
			//在这里添加自定义验证
			
			return disableSubmit(finalResult,'submitButton');
		}});
	</script>
</duowan:override>

<%-- jsp模板继承,具体使用请查看: http://code.google.com/p/rapid-framework/wiki/rapid_jsp_extends --%>
<%@ include file="base.jsp" %>