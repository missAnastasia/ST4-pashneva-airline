<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Login Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
    <div id="wrapper">
        <jsp:include page="WEB-INF/jsp/_header.jsp"/>
        <div id="page" class="container">
            <div class="title">
                <h2><fmt:message key="login_jsp.h2"/></h2>
            </div>
            <div class="main-content-div">
                <form  action="${pageContext.request.contextPath}/controller?command=loginCommand" autocomplete="on" method="post">
                    <h3>
                        <label><fmt:message key="login_jsp.label.login"/></label><br>
                        <input class="full-input-field" id="login" name="login" type="email" placeholder="example@site.com" maxlength="256" value="${user.login}" required/>
                        <span class="form__error"><fmt:message key="validation.login"/></span>
                    </h3>
                    <h3>
                        <label><fmt:message key="login_jsp.label.password"/></label><br>
                        <input class="full-input-field" id="password" name="password" minlength="8" maxlength="45" type="password" placeholder="Xx1_" pattern="\w+" required/>
                        <span class="form__error"><fmt:message key="validation.password"/></span>
                    </h3>
                    <h3 class="keeplogin">
                        <input type="checkbox" name="rememberMe" value="Y" class="check-box" id="loginkeeping"/>
                        <label for="loginkeeping"><fmt:message key="login_jsp.label.remember_me"/></label>
                    </h3>
                    <input type="submit" class="full-width-button" value="<fmt:message key="login_jsp.submit"/>"/>
                </form>
            </div>
        </div>
        <jsp:include page="WEB-INF/jsp/_footer.jsp"/>
    </div>
</body>
</html>
