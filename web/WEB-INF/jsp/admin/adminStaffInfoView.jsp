<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Staff Info" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="staff_info_admin_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <div id="post-info-div">
            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="staff_info_admin_jsp.first_name"/>
                </label>
                <label class="label-v">${staff_item.getUser().getFirstName()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="staff_info_admin_jsp.second_name"/>
                </label>
                <label class="label-v">${staff_item.getUser().getSecondName()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="staff_info_admin_jsp.post"/>
                </label>
                <label class="label-v">${staff_item.getPost().getName()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="staff_info_admin_jsp.login"/>
                </label>
                <label class="label-v">${staff_item.getUser().getLogin()}</label>
            </div>
        </div>

        <div id="change-div">
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/redirect?command=getAdminEditStaffPageCommand" method="post">
                    <input type="hidden" name="staff_id" value="${staff_item.getId()}">
                    <input type="submit" value="<fmt:message key="staff_info_admin_jsp.edit_staff"/>" id="staff-change-button">
                </form>
            </div>
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/controller?command=deleteAdminStaffCommand" method="post">
                    <input type="hidden" name="staff_id" value="${staff_item.getId()}">
                    <input type="submit" value="<fmt:message key="staff_info_admin_jsp.delete_staff"/>" id="staff-delete-button">
                </form>
            </div>
            <%-- <div class="change-a">
                 <form action="${pageContext.request.contextPath}/redirect?command=getChangePasswordPageCommand" method="post">
                     <input type="submit" value="<fmt:message key="userInfo_jsp.link.change_password"/>" id="user-password-change-button">
                 </form>
             </div>--%>
        </div>

    </div>
</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>

