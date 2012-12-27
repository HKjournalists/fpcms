<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<script type="text/javascript">
	parent.location='${ctx}/admin/cmschannel/index.do?<%=request.getQueryString()%>'; 
</script>