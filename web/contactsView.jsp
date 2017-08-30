<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Contact Us" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="WEB-INF/jsp/_header.jsp"></jsp:include>
<jsp:include page="WEB-INF/jsp/_menu_staff.jsp"></jsp:include>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="contacts_jsp.h2"></fmt:message></h2>
    </div>

    <div>
        <iframe class="map" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2564.9602883676685!2d36.230289815530945!3d49.99335397941486!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4127a0f01b926203%3A0x9a2ea1138401f886!2z0LLRg9C70LjRhtGPINCh0YPQvNGB0YzQutCwLCAyLCDQpdCw0YDQutGW0LIsINCl0LDRgNC60ZbQstGB0YzQutCwINC-0LHQu9Cw0YHRgtGM!5e0!3m2!1sru!2sua!4v1502998409540" width="800" height="600" frameborder="0" style="border:0" allowfullscreen></iframe>
    </div>


</div>

<jsp:include page="WEB-INF/jsp/_footer.jsp"></jsp:include>

</body>
</html>
