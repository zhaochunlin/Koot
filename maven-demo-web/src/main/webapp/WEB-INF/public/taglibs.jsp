<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	String locale = "zh_CN";  // 默认使用中文
	if(request.getLocale() != null){
		locale = request.getLocale().toString();
	}
%>
<c:set var="permissions" value="${sessionScope.permissions}" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="locale" value="<%=locale %>" />
