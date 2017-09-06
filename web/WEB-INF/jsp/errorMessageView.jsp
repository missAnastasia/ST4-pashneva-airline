<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Error"/>
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="_header.jsp"/>
<cstm:menu_user_role/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="error_jsp.h2"/></h2>
    </div>

				<%-- this way we obtain an information about an exception (if it has been occurred) --%>
				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="err_message" value="${requestScope['javax.servlet.error.message']}"/>
				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
				
				<c:if test="${not empty code}">
					<h3>Error code: ${code}</h3><br>
				</c:if>			
				
				<c:if test="${not empty err_message}">
					<h3>${err_message}</h3><br>
				</c:if>
				
				<c:if test="${not empty exception}">
					<% exception.printStackTrace(new PrintWriter(out)); %><<br>
				</c:if>
				
				<%-- if we get this page using forward --%>
				<c:if test="${not empty requestScope.message}">
					<h3>${requestScope.message}</h3><br>
				</c:if>
</div>
	<jsp:include page="_footer.jsp"/>
</body>
</html>