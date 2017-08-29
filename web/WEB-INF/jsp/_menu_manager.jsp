<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<div id="menu" class="container">
    <ul>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}/redirect?command=getProductsPageCommand" accesskey="1" title=""><fmt:message key="header_jsp.menu.client.homepage"/></a></li>
    </ul>
</div>