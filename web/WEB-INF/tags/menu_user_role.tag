<%@ tag body-content="empty" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<c:choose>
    <c:when test="${sessionScope.user.getRole().getName() eq 'dispatcher'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/WEB-INF/jsp/_menu_dispatcher.jsp"/>
    </c:when>
    <c:when test="${sessionScope.user.getRole().getName() eq 'admin'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/WEB-INF/jsp/_menu_admin.jsp"/>
    </c:when>
    <c:when test="${sessionScope.user.getRole().getName() eq 'staff'}">
        <jsp:include page="${pageContext.servletContext.contextPath}/WEB-INF/jsp/_menu_staff.jsp"/>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>
