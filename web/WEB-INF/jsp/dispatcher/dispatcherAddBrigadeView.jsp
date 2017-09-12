<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Sign In Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>

<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="register_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <form  action="<%--controller?command=registerClientCommand--%>" autocomplete="on" method="post">
            <h3>
                <label class="number"><fmt:message key="add_brigade_dispatcher_jsp.number"/></label><br>
                <input id="number" name="brigade_number" minlength="4" maxlength="7" required type="text" placeholder="XX-0000" pattern="^[A-Z]{2}[-][0-9]{1,4}$"/>
                <span class="form__error"><fmt:message key="validation.first_name"/></span>
            </h3>
            <h3>
                <label class="staff_list"><fmt:message key="add_brigade_dispatcher_jsp.staff_list"/></label><br>
            </h3>

            <c:forEach items="${posts}" var="post">
                <div class="one-staff-info-div">
                    <label class="label-n">
                            ${post.getName()}:
                    </label>
                    <ul class="staff-list">
                        <c:forEach items="${staff}" var="staff_item">
                            <%--<c:if test="${staff_item.getPost().getId() == post.getId()}">--%>
                                <li class="staff-list-item"><label class="label-v"><input type="checkbox" name="staff_id" value="${staff_item.getId()}">${staff_item.getUser().getFirstName()} ${staff_item.getUser().getSecondName()}</label></li>
                            <%--</c:if>--%>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>
        </form>
    </div>

</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>
