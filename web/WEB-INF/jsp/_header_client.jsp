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
                            <li><a href=""><fmt:message key="header_jsp.client.orders"/></a></li>
                            <li><a href="redirect?command=getUserInfoPageCommand"><fmt:message key="header_jsp.account"/></a></li>
                            <li><a href="controller?command=logoutCommand"><fmt:message key="header_jsp.logout"/></a></li>
                        </ul>
                    </li>
                </ul>
                    <%--<a class="login-a">${user.firstName} ${user.secondName}</a>--%>
                </c:when>
                <c:otherwise>
                    <a class="login-a" href="${pageContext.request.contextPath}/loginView.jsp" rel="nofollow"><fmt:message key="header_jsp.login"/></a>
                </c:otherwise>
            </c:choose>
            </div>
        </div>

<%--<div style="background: #E0E0E0; height: 55px; padding: 5px;">
    <div style="float: left">
        <h1><fmt:message key="header_jsp.h1"/></h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">

        <!-- User store in session with attribute: loginedUser -->
        <c:choose>
            <c:when test="${user != null}">
                Hello,
                <div class="dropdown">
                    <button onclick="myFunction()" class="dropbtn">
                        <b>${user.firstName}</b> <b>${user.secondName}</b>
                    </button>
                    <div id="myDropdown" class="dropdown-content">
                        <li type="none"><a href="controller?command=userInfoCommand">
                            <fmt:message key="header_jsp.l1.account"/>
                        </a></li>
                        <li type="none"><a href="controller?command=logoutCommand">
                            <fmt:message key="header_jsp.l1.logout"/>
                        </a></li>
                    </div>
                </div>

            </c:when>
            <c:otherwise>
                <a href="controller?command=getLoginPageCommand">
                    <fmt:message key="header_jsp.l1.login"/>
                </a>
            </c:otherwise>
        </c:choose>

        <br/>
        Search <input name="search">

    </div>

</div>--%>
