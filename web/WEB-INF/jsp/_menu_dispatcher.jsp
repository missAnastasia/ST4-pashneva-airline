<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<div id="menu" class="container">
    <ul>
        <li class="current_page_item"><a href="${pageContext.request.contextPath}/redirect?command=getDispatcherFlightsPageCommand" accesskey="1" title=""><fmt:message key="menu.dispatcher.flights"/></a></li>
    </ul>
</div>