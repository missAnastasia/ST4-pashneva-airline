<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Sign In Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>

<jsp:include page="WEB-INF/jsp/_header_client.jsp"/>
<jsp:include page="WEB-INF/jsp/_menu_client.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="register_jsp.h2"/></h2>
    </div>

    <div class="login-div">
        <form  action="controller?command=registerClientCommand" autocomplete="on" method="post">
            <h3>
                <label for="first_name" class="first_name"><fmt:message key="register_jsp.label.first_name"/></label><br>
                <input id="first_name" name="firstName" minlength="1" maxlength="45" required type="text" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                <span class="form__error"><fmt:message key="validation.first_name"/></span>
            </h3>
            <h3>
                <label for="second_name" class="second_name"><fmt:message key="register_jsp.label.second_name"/></label><br>
                <input id="second_name" name="secondName" minlength="1" maxlength="45" required type="text" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                <span class="form__error"><fmt:message key="validation.second_name"/></span>
            </h3>
            <h3>
                <label for="login" class="login"><fmt:message key="register_jsp.label.login"/></label><br>
                <input id="login" name="login" type="email" placeholder="example@site.com" maxlength="256" required/>
                <span class="form__error"><fmt:message key="validation.login"/></span>
            </h3>
            <h3>
                <label for="password" class="password"><fmt:message key="register_jsp.label.password"/></label><br>
                <input id="password" name="password" minlength="8" maxlength="45" required type="password" placeholder="Xx1_" pattern="\w+"/>
                <span class="form__error"><fmt:message key="validation.password"/></span>
            </h3>
            <input type="submit" value="<fmt:message key="register_jsp.sign_in"/>"/>

            <p class="change_link">
                <fmt:message key="register_jsp.label.already_have_account"/>
                <a href="controller?command=getLoginPageCommand" class="to_register"><fmt:message key="register_jsp.log_in"/></a>
            </p>
        </form>
    </div>

</div>

<jsp:include page="WEB-INF/jsp/_footer.jsp"/>

</body>
</html>
