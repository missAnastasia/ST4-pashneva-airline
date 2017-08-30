<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="User Account Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu_staff.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="userInfo_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <div id="position-info-div">
            <div class="one-position-info-div">
                <label class="label-n">
                    <fmt:message key="userInfo_jsp.label.first_name"/>
                </label>
                <label class="label-v">${user.firstName}</label>
            </div>

            <div class="one-position-info-div">
                <label class="label-n">
                    <fmt:message key="userInfo_jsp.label.second_name"/>
                </label>
                <label class="label-v">${user.secondName}</label>
            </div>

            <div class="one-position-info-div">
                <label class="label-n">
                    <fmt:message key="userInfo_jsp.label.login"/>
                </label>
                <label class="label-v">${user.login}</label>
            </div>
        </div>

        <div id="change-div">
            <div class="change-a">
                <a href="${pageContext.request.contextPath}/redirect?command=getChangeUserDataPageCommand">
                    <fmt:message key="userInfo_jsp.link.change_data"/>
                </a>
            </div>
            <div class="change-a">
                <a href="${pageContext.request.contextPath}/redirect?command=getChangePasswordPageCommand">
                    <fmt:message key="userInfo_jsp.link.change_password"/>
                </a>
            </div>
        </div>

    </div>
</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>

