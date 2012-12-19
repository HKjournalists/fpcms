<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '${ctx}/js/kindeditor/plugins/code/prettify.css',
				allowFileManager : false,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms[0].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms[0].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
		
	<input type="hidden" id="id" name="id" value="${cmsContent.id}"/>

	<tr>	
		<td class="tdLabel">
			频道ID:
		</td>		
		<td>
		<form:input path="channelCode" id="channelCode" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="channelCode"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			标签:
		</td>		
		<td>
		<form:input path="tags" id="tags" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="tags"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网页头title:
		</td>		
		<td>
		<form:input path="headTitle" id="headTitle" cssClass="" maxlength="200" />
		<font color='red'><form:errors path="headTitle"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			标题:
		</td>		
		<td>
		<form:input path="title" id="title" cssClass="" maxlength="200" />
		<font color='red'><form:errors path="title"/></font>
		</td>
	</tr>	

	<tr>	
		<td class="tdLabel">
			作者:
		</td>		
		<td>
		<form:input path="author" id="author" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="author"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			创建时间:
		</td>		
		<td>
		<input value='<fmt:formatDate value="${cmsContent.dateCreated}" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateCreated" name="dateCreated"  maxlength="0" class="" />
		<font color='red'><form:errors path="dateCreated"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			更新时间:
		</td>		
		<td>
		<input value='<fmt:formatDate value="${cmsContent.dateLastModified}" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateLastModified" name="dateLastModified"  maxlength="0" class="" />
		<font color='red'><form:errors path="dateLastModified"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			网站:
		</td>		
		<td>
		<form:input path="site" id="site" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="site"/></font>
		</td>
	</tr>	

	<tr>	
		<td class="tdLabel">
			等级:
		</td>		
		<td>
		<form:input path="level" id="level" cssClass="" maxlength="50" />
		<font color='red'><form:errors path="level"/></font>
		</td>
	</tr>
			
	<tr>	
		<td class="tdLabel">
			内容:
		</td>		
		<td>
		<form:textarea path="content" id="content" maxlength="65535" cssStyle="width:800px;height:600px;visibility:hidden;"/>
		<font color='red'><form:errors path="content"/></font>
		</td>
	</tr>	
	

	
		