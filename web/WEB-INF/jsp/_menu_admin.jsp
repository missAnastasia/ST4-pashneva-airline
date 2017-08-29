<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<div id="menu" class="container">
    <ul>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}/homeView.jsp" accesskey="1" title=""><fmt:message key="header_jsp.menu.admin.client_accounts"/></a></li>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}/homeView.jsp" accesskey="2" title=""><fmt:message key="header_jsp.menu.admin.mngr_accounts"/></a></li>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}/contactsView.jsp" accesskey="3" title=""><fmt:message key="header_jsp.menu.admin.cars"/></a></li>
    </ul>
</div>