<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="About Us" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="WEB-INF/jsp/_header.jsp"></jsp:include>
<jsp:include page="WEB-INF/jsp/_menu_staff.jsp"></jsp:include>

</div>
<div number="page" class="container">
<div class="title">
    <h2><fmt:message key="about_jsp.h2"></fmt:message></h2>
</div>

    <div class="paragraph">
        <p><fmt:message key="about_jsp.p"></fmt:message></p>
    </div>


</div>

<jsp:include page="WEB-INF/jsp/_footer.jsp"></jsp:include>

</body>
</html>