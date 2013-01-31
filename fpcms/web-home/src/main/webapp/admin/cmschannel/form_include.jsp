<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

	<input type="hidden" id="id" name="id" value="${cmsChannel.id}"/>

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
	
	<tr>	
		<td class="tdLabel">
			频道代码:
		</td>		
		<td>
		<form:input path="channelCode" id="channelCode" cssClass="" maxlength="50" size="80"/>
		<font color='red'><form:errors path="channelCode"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			频道名称:
		</td>		
		<td>
		<form:input path="channelName" id="channelName" cssClass="" maxlength="50" size="80"/>
		<font color='red'><form:errors path="channelName"/></font>
		</td>
	</tr>	

	<tr>	
		<td class="tdLabel">
			频道描述:
		</td>		
		<td>
		<form:input path="channelDesc" id="channelDesc" cssClass="" maxlength="200" size="200"/>
		<font color='red'><form:errors path="channelDesc"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			父亲ID:
		</td>		
		<td>
		<form:input path="parentId" id="parentId" cssClass="validate-integer " maxlength="19" size="80"/>
		<font color='red'><form:errors path="parentId"/></font>
		</td>
	</tr>	

	<tr>	
		<td class="tdLabel">
			等级:
		</td>		
		<td>
		<form:input path="level" id="level" cssClass="" maxlength="50" size="80"/>
		<font color='red'><form:errors path="level"/></font>
		</td>
	</tr>	
		
	<tr>	
		<td class="tdLabel">
			网站:
		</td>		
		<td>
		<form:input path="site" id="site" cssClass="" maxlength="50" size="80"/>
		<font color='red'><form:errors path="site"/></font>
		</td>
	</tr>	
		
	<tr>	
		<td class="tdLabel">
			更新时间:
		</td>		
		<td>
		<input value='<fmt:formatDate value="${cmsChannel.dateLastModified}" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateLastModified" name="dateLastModified"  maxlength="0" class="" size="80" />
		<font color='red'><form:errors path="dateLastModified"/></font>
		</td>
	</tr>	

	<tr>	
		<td class="tdLabel">
			创建时间:
		</td>		
		<td>
		<input value='<fmt:formatDate value="${cmsChannel.dateCreated}" pattern="yyyy-MM-dd"/>' onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" id="dateCreated" name="dateCreated"  maxlength="0" class="" size="80"/>
		<font color='red'><form:errors path="dateCreated"/></font>
		</td>
	</tr>
		
	<tr>	
		<td class="tdLabel">
			作者:
		</td>		
		<td>
		<form:input path="author" id="author" cssClass="" maxlength="50" size="80"/>
		<font color='red'><form:errors path="author"/></font>
		</td>
	</tr>	

	<tr>	
		<td class="tdLabel">
			关键字:
		</td>		
		<td>
		<form:input path="keyword" id="keyword" cssClass="" maxlength="50" size="80"/>
		<font color='red'><form:errors path="keyword"/></font>
		</td>
	</tr>
		
	<tr>	
		<td class="tdLabel">
			内容:
		</td>		
		<td>
		<form:textarea path="content" id="content" maxlength="65535" cssStyle="width:600px;height:600px;visibility:hidden;"/>
		<font color='red'><form:errors path="content"/></font>
		</td>
	</tr>
	
	