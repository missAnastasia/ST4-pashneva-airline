<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<div id="menu" class="container">
    <ul>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}/homeView.jsp" accesskey="1" title=""><fmt:message key="header_jsp.menu.client.homepage"/></a></li>
        <c:choose>
            <c:when test="${user != null}">
                <li class="current_page_item"><a href="${pageContext.request.contextPath}/redirect?command=getProductsPageCommand" accesskey="2" title=""><fmt:message key="header_jsp.menu.client.car_park"/></a></li>
                <li class="current_page_item"><a href="${pageContext.request.contextPath}/redirect?command=getConditionsPageCommand" accesskey="3" title=""><fmt:message key="header_jsp.menu.client.rental_conditions"/></a></li>
            </c:when>
        </c:choose>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}/contactsView.jsp" accesskey="4" title=""><fmt:message key="header_jsp.menu.client.contact_us"/></a></li>
    </ul>
</div>