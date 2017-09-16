<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Success" scope="page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<div id="wrapper">
    <jsp:include page="_header.jsp"/>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="success_jsp.h2"/></h2>
    </div>
    <div>
        <h3>${requestScope.message}</h3>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</div>
</body>
</html>