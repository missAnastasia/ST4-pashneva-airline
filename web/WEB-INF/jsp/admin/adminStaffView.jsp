<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Staff" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>

<jsp:include page="/WEB-INF/jsp/_header.jsp"/>
<jsp:include page="/WEB-INF/jsp/_menu_admin.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="staff_admin_jsp.h2"/></h2>
    </div>

    <div id="content-div">
        <div id="options-div">
            <div id="search-div" class="login-div">
                <div class="criterion_search-div">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminAddStaffPageCommand" autocomplete="on" method="post">
                        <input type="submit" value="<fmt:message key="staff_admin_jsp.add_staff"/>"/>
                    </form>
                </div>

                <div class="criterion_search-div" id="staff-by-post-search">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminStaffPageCommand" autocomplete="on" method="post">
                        <label class="label-select"><fmt:message key="staff_admin_jsp.search.post"/></label>
                        <select name="post_id" class="entity-select">
                            <c:forEach items="${posts}" var="post_var">
                                <c:set var="selected" value="${post_var.getId() == post_id ? 'selected' : '' }"/>
                                <option value="${post_var.getId()}" <%--${selected}--%>>${post_var.getName()}</option>
                            </c:forEach>
                        </select><br>
                        <input type="submit" value="<fmt:message key="staff_admin_jsp.search.submit"/>" class="search-button"/>
                    </form>
                </div>
            </div>
        </div>
        <div id="content-table-div">
            <div id="items-div">
                <c:forEach items="${staff}" var="staff_item">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminStaffInfoPageCommand" method="post" id="staff-form">
                        <div class="item-div">
                            <table class="item-table">
                                <tr>
                                    <td colspan="2">
                                        <h3><button class="staff-name-link" type="submit" name="staff_id" value="${staff_item.getId()}" form="staff-form">${staff_item.getUser().getFirstName()} ${staff_item.getUser().getSecondName()}</button></h3>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="staff-name-td"><h3><fmt:message key="staff_admin_jsp.post"/></h3></td>
                                    <td class="staff-post-td">
                                            ${staff_item.getPost().getName()}
                                    </td>
                                </tr>
                                <tr>
                                    <td class="staff-name-td"><h3><fmt:message key="staff_admin_jsp.login"/></h3></td>
                                    <td class="staff-post-td">
                                            ${staff_item.getUser().getLogin()}
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </form>
                </c:forEach>
                <div>
                    <h3>${message}</h3>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/_footer.jsp"/>

</body>
</html>
