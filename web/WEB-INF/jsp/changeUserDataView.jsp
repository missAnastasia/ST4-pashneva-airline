<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="User Account Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="_header.jsp"/>
<cstm:menu_user_role/>
</div>
<div id="page" class="container">
        <div class="title">
            <h2><fmt:message key="userInfo_jsp.h2"/></h2>
        </div>
    <div class="login-div">
        <form  action="${pageContext.request.contextPath}/controller?command=updateUserInfoCommand" autocomplete="off" method="post">
            <h3>
                <label for="first_name" class="first_name"><fmt:message key="changeData_jsp.label.first_name"/></label><br>
                <input value="${user.firstName}" id="first_name" name="firstName" minlength="1" maxlength="45" required type="text" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                <span class="form__error"><fmt:message key="validation.first_name"/></span>
            </h3>
            <h3>
                <label for="second_name" class="second_name"><fmt:message key="changeData_jsp.label.second_name"/></label><br>
                <input value="${user.secondName}" id="second_name" name="secondName" minlength="1" maxlength="45" required type="text" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                <span class="form__error"><fmt:message key="validation.second_name"/></span>
            </h3>
            <h3>
                <label for="login" class="login"><fmt:message key="changeData_jsp.label.login"/></label><br>
                <input value="${user.login}" id="login" name="login" type="email" placeholder="example@site.com" minlength="8" maxlength="45" required/>
                <span class="form__error"><fmt:message key="validation.login"/></span>
            </h3>
            <h3>
                <label for="old_password" class="password"><fmt:message key="changeData_jsp.label.password"/></label><br>
                <input id="old_password" name="old_password" minlength="8" maxlength="45" required type="password" placeholder="Xx1_" pattern="\w+"/>
                <span class="form__error"><fmt:message key="validation.password"/></span>
            </h3>
            <input type="submit" value="<fmt:message key="changeData_jsp.submit"/>"/>
        </form>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>
