<%@page import="com.fpcms.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>


	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>博客地址:
		</td>		
		<td>
		<form:input path="blogUrl" id="blogUrl" cssClass="required " maxlength="100" size="100"/>
		<font color='red'><form:errors path="blogUrl"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			博客RPC地址:
		</td>		
		<td>
		<form:input path="blogRpcUrl" id="blogRpcUrl" cssClass="" maxlength="100"  size="100"/>
		<font color='red'><form:errors path="blogRpcUrl"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			blogName:
		</td>		
		<td>
		<form:input path="blogName" id="blogName" cssClass="" maxlength="100"  size="100"/>
		<font color='red'><form:errors path="blogName"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>username:
		</td>		
		<td>
		<form:input path="username" id="username" cssClass="required " maxlength="20"  size="100"/>
		<font color='red'><form:errors path="username"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			<span class="required">*</span>password:
		</td>		
		<td>
		<form:input path="password" id="password" cssClass="required " maxlength="20"  size="100"/>
		<font color='red'><form:errors path="password"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			tags:
		</td>		
		<td>
		<form:input path="tags" id="tags" cssClass="" maxlength="100"  size="100"/>
		<font color='red'><form:errors path="tags"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			categories:
		</td>		
		<td>
		<form:input path="categories" id="categories" cssClass="" maxlength="100"  size="100"/>
		<font color='red'><form:errors path="categories"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			博客RPC类型:
		</td>		
		<td>
		<form:input path="blogRpcApi" id="blogRpcApi" cssClass="" maxlength="30"  size="100"/>
		<font color='red'><form:errors path="blogRpcApi"/></font>
		</td>
	</tr>	
	
	<tr>	
		<td class="tdLabel">
			博客描述:
		</td>		
		<td>
		<form:input path="blogDesc" id="blogDesc" cssClass="" maxlength="100"  size="100"/>
		<font color='red'><form:errors path="blogDesc"/></font>
		</td>
	</tr>	

	<tr>	
		<td class="tdLabel">
			博客发送数:
		</td>		
		<td>
		<form:input path="blogPostCount" id="blogPostCount" cssClass="" maxlength="100"  size="100"/>
		<font color='red'><form:errors path="blogPostCount"/></font>
		</td>
	</tr>
	
	<tr>	
		<td class="tdLabel">
			博客发送的api实现class:
		</td>		
		<td>
		<form:input path="blogRpcApiClass" id="blogRpcApiClass" cssClass="" maxlength="100"  size="100"/>
		<font color='red'><form:errors path="blogRpcApiClass"/></font>
		</td>
	</tr>	
