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
        <h2><fmt:message key="staff_info_admin_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <div id="properties-div">
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="staff_info_admin_jsp.first_name"/>
                </label>
                <label class="label-v">${staff_item.getUser().getFirstName()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="staff_info_admin_jsp.second_name"/>
                </label>
                <label class="label-v">${staff_item.getUser().getSecondName()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="staff_info_admin_jsp.post"/>
                </label>
                <label class="label-v">${staff_item.getPost().getName()}</label>
            </div>
            <div class="one-property-div">
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
                    <input type="submit" class="auto-width-button" value="<fmt:message key="staff_info_admin_jsp.edit_staff"/>">
                </form>
            </div>
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/controller?command=deleteAdminStaffCommand" method="post">
                    <input type="hidden" name="staff_id" value="${staff_item.getId()}">
                    <input type="submit" value="<fmt:message key="staff_info_admin_jsp.delete_staff"/>" id="entity-delete-button">
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>

