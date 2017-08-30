<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Login Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>

<jsp:include page="/WEB-INF/jsp/_header.jsp"/>
<jsp:include page="/WEB-INF/jsp/_menu_admin.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="page_title.clients_accounts"/></h2>
    </div>

</div>

<jsp:include page="/WEB-INF/jsp/_footer.jsp"/>

</body>
</html>
