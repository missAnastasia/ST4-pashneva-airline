<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="User Account Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<div id="wrapper">
    <jsp:include page="_header.jsp"/>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="userInfo_jsp.h2"/></h2>
    </div>

    <div class="main-content-div">
        <form  action="controller?command=changePasswordCommand" autocomplete="off" method="post">
            <h3>
                <label class="password"><fmt:message key="changePassword_jsp.label.old_password"/></label><br>
                <input class="full-input-field" name="old_password" minlength="8" maxlength="45" required type="password" placeholder="Xx1_" pattern="\w+"/>
                <span class="form__error"><fmt:message key="validation.password"/></span>
            </h3>
            <h3>
                <label class="password"><fmt:message key="changePassword_jsp.label.new_password"/></label><br>
                <input class="full-input-field" name="new_password" minlength="8" maxlength="45" required type="password" placeholder="Xx1_" pattern="\w+"/>
                <span class="form__error"><fmt:message key="validation.password"/></span>
            </h3>
            <%--<h3 class="keeplogin">
                <input type="checkbox" name="rememberMe" value="Y" number="loginkeeping"/>
                <label for="loginkeeping"><fmt:message key="login_jsp.label.remember_me"/></label>
            </h3>--%>
            <input type="submit" class="full-width-button" value="<fmt:message key="changePassword_jsp.submit"/>"/>

            <%--<p class="change_link">
                <fmt:message key="register_jsp.label.already_have_account"/>
                <a href="controller?command=getLoginPageCommand" class="to_register"><fmt:message key="register_jsp.log_in"/></a>
            </p>--%>
        </form>
    </div>
</div>

<jsp:include page="_footer.jsp"/>
</div>
</body>
</html>
