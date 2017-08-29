<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="User Account Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="_header_client.jsp"/>
<jsp:include page="_menu_client.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="userInfo_jsp.h2"/></h2>
    </div>

    <div class="login-div">
        <form  action="controller?command=changePasswordCommand" autocomplete="off" method="post">
            <%--<h3>
                <label for="first_name" class="first_name"><fmt:message key="register_jsp.label.first_name"/></label><br>
                <input value="${user.firstName}" id="first_name" name="firstName" minlength="1" maxlength="45" required type="text" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                <span class="form__error"><fmt:message key="validation.first_name"/></span>
            </h3>
            <h3>
                <label for="second_name" class="second_name"><fmt:message key="register_jsp.label.second_name"/></label><br>
                <input value="${user.secondName}" id="second_name" name="secondName" minlength="1" maxlength="45" required type="text" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                <span class="form__error"><fmt:message key="validation.second_name"/></span>
            </h3>
            <h3>
                <label for="login" class="login"><fmt:message key="register_jsp.label.login"/></label><br>
                <input value="${user.login}" id="login" name="login" type="email" placeholder="example@site.com" minlength="8" maxlength="45" required/>
                <span class="form__error"><fmt:message key="validation.login"/></span>
            </h3>--%>
            <h3>
                <label for="old_password" class="password"><fmt:message key="changePassword_jsp.label.old_password"/></label><br>
                <input id="old_password" name="old_password" minlength="8" maxlength="45" required type="password" placeholder="Xx1_" pattern="\w+"/>
                <span class="form__error"><fmt:message key="validation.password"/></span>
            </h3>
            <h3>
                <label for="new_password" class="password"><fmt:message key="changePassword_jsp.label.new_password"/></label><br>
                <input id="new_password" name="new_password" minlength="8" maxlength="45" required type="password" placeholder="Xx1_" pattern="\w+"/>
                <span class="form__error"><fmt:message key="validation.password"/></span>
            </h3>
            <%--<h3 class="keeplogin">
                <input type="checkbox" name="rememberMe" value="Y" id="loginkeeping"/>
                <label for="loginkeeping"><fmt:message key="login_jsp.label.remember_me"/></label>
            </h3>--%>
            <input type="submit" value="<fmt:message key="changePassword_jsp.submit"/>"/>

            <%--<p class="change_link">
                <fmt:message key="register_jsp.label.already_have_account"/>
                <a href="controller?command=getLoginPageCommand" class="to_register"><fmt:message key="register_jsp.log_in"/></a>
            </p>--%>
        </form>
    </div>
</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>
