<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Staff Info" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstmtf:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="edit_staff_admin_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <form action="${pageContext.request.contextPath}/controller?command=editAdminStaffCommand" autocomplete="on" method="post">
                <h3>
                    <label class="label-n"><fmt:message key="edit_staff_admin_jsp.first_name"/></label><br>
                    <input class="full-input-field" name="first_name" minlength="1" maxlength="45" required type="text" value="${staff_item.getUser().getFirstName()}" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                    <span class="form__error"><fmt:message key="validation.first_name"/></span>
                </h3>
                <h3>
                    <label class="label-n"><fmt:message key="edit_staff_admin_jsp.second_name"/></label><br>
                    <input class="full-input-field" name="second_name" minlength="1" maxlength="45" required type="text" value="${staff_item.getUser().getSecondName()}" placeholder="Xxx" pattern="[a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+"/>
                    <span class="form__error"><fmt:message key="validation.second_name"/></span>
                </h3>
                <h3>
                    <label class="label-n"><fmt:message key="edit_staff_admin_jsp.login"/></label><br>
                    <input class="full-input-field" name="login" type="email" placeholder="example@site.com" maxlength="256" required  value="${staff_item.getUser().getLogin()}"/>
                    <span class="form__error"><fmt:message key="validation.login"/></span>
                </h3>
            <label class="label-select"><fmt:message key="edit_staff_admin_jsp.post"/></label>
            <select name="post_id" class="entity-select" id="bottom-margin-select">
                <c:forEach items="${posts}" var="post_var">
                    <c:set var="selected" value="${post_var.getId() == staff_item.getPost().getId() ? 'selected' : '' }"/>
                    <option value="${post_var.getId()}" ${selected}>${post_var.getName()}</option>
                </c:forEach>
            </select><br>
            <input type="hidden" name="staff_id" value="${staff_item.getId()}">
            <input type="submit" class="full-width-button" value="<fmt:message key="edit_staff_admin_jsp.submit"/>"/>
        </form>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
