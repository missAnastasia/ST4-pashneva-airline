<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Success Message" scope="page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu_staff.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="error_page_jsp.title"/></h2>
    </div>
    <div>
        <h3>${requestScope.message}</h3>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>