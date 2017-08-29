<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Home Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="WEB-INF/jsp/_header_client.jsp"/>
<jsp:include page="WEB-INF/jsp/_menu_client.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="page_title.home"/></h2>
    </div>
    <div id="about_p">
        <p><fmt:message key="about_jsp.p"></fmt:message></p>
    </div>
    <div class="column1">
        <div class="title">
            <h2><fmt:message key="homeView_jsp.container.column1.h"/></h2>
        </div>
        <p><fmt:message key="homeView_jsp.container.column1.p"/></p>
    </div>
    <div class="column2">
        <div class="title">
            <h2><fmt:message key="homeView_jsp.container.column2.h"/></h2>
        </div>
        <img src="${pageContext.servletContext.contextPath}/resources/style/images/column2.jpg" alt="" />
        <p><fmt:message key="homeView_jsp.container.column2.p1"/><br><fmt:message key="homeView_jsp.container.column2.p2"/><br>
            <fmt:message key="homeView_jsp.container.column2.p3"/><br><fmt:message key="homeView_jsp.container.column2.p4"/></p>
    </div>
    <div class="column3">
        <div class="title">
            <h2><fmt:message key="homeView_jsp.container.column3.h"/></h2>
        </div>
        <img src="${pageContext.servletContext.contextPath}/resources/style/images/column3.jpeg" alt="" />
        <p><fmt:message key="homeView_jsp.container.column3.p"/></p>
    </div>
</div>
<jsp:include page="WEB-INF/jsp/_footer.jsp"/>

</body>
</html>
