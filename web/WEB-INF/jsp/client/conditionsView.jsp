<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Conditions Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header_client.jsp"/>
<jsp:include page="../_menu_client.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="conditions_jsp.h2"/></h2>
    </div>

    <div class="paragraph">
        <h3><fmt:message key="conditions_jsp.p1.title"/></h3>
        <ul>
        <li>
            <fmt:message key="conditions_jsp.p1.li1"/>
        </li>
        <li>
            <fmt:message key="conditions_jsp.p1.li2"/>
        </li>
        </ul>

    </div>
    <div class="paragraph">
        <h3><fmt:message key="conditions_jsp.p2.title"/></h3>
        <ul >
            <li>
                <fmt:message key="conditions_jsp.p2.li1"/>
            </li>
            <li>
                <fmt:message key="conditions_jsp.p2.li2"/>
            </li>
            <li>
                <fmt:message key="conditions_jsp.p2.li3"/>
            </li>
        </ul>
    </div>
    <div class="paragraph">
        <h3><fmt:message key="conditions_jsp.p3.title"/></h3>
        <ul>
            <li>
                <fmt:message key="conditions_jsp.p3.li1"/>
            </li>
            <li>
                <fmt:message key="conditions_jsp.p3.li2"/>
            </li>
        </ul>
    </div>
</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>
