<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="My Account" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="_header.jsp"/>
<cstmtf:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="userInfo_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <div id="properties-div">
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="userInfo_jsp.label.first_name"/>
                </label>
                <label class="label-v">${user.firstName}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="userInfo_jsp.label.second_name"/>
                </label>
                <label class="label-v">${user.secondName}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="userInfo_jsp.label.login"/>
                </label>
                <label class="label-v">${user.login}</label>
            </div>
        </div>
        <div id="change-div">
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/redirect?command=getChangePasswordPageCommand" method="post">
                    <input type="submit" class="auto-width-button" value="<fmt:message key="userInfo_jsp.link.change_password"/>" id="auto-width-button">
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>

