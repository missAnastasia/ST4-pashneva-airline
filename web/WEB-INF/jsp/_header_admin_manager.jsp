<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>

<div id="wrapper">
    <div id="header-wrapper">
        <div id="header" class="container">
            <div id="logo">
                <h1><a href="${pageContext.request.contextPath}/homeView.jsp"><fmt:message key="header_jsp.title"/></a></h1>
                <p><fmt:message key="header_jsp.car_rental_title"/></p>
            </div>
            <div id="social">
                <c:choose>
                    <c:when test="${user != null}">
                        <ul class="menu" >
                            <li><a>${user.firstName} ${user.secondName}</a>
                                <ul class="submenu">
                                    <li><a href="controller?command=getUserInfoPageCommand"><fmt:message key="header_jsp.account"/></a></li>
                                    <li><a href="controller?command=logoutCommand"><fmt:message key="header_jsp.logout"/></a></li>
                                </ul>
                            </li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <a class="login-a" href="${pageContext.request.contextPath}/loginView.jsp" rel="nofollow"><fmt:message key="header_jsp.login"/></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
