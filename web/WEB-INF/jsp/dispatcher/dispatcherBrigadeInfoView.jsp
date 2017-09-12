<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Request Info" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="brigade_info_dispatcher_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <div id="staff-info-div">
            <div class="staff-number-info-div">
                <label class="label-n">
                    <fmt:message key="brigade_info_dispatcher_jsp.number"/>
                </label>
                <label class="label-v">${brigade.getNumber()}</label>
            </div>
            <h3 id="staff-list-h3"><fmt:message key="brigade_info_dispatcher_jsp.staff_list"/></h3>

            <c:forEach items="${posts}" var="post">
            <div class="one-staff-info-div">
                <label class="label-n">
                    ${post.getName()}:
                </label>
                <ul class="staff-list">
                    <c:forEach items="${staff}" var="staff_item">
                    <c:if test="${staff_item.getPost().getId() == post.getId()}">
                        <li class="staff-list-item"><label class="label-v">${staff_item.getUser().getFirstName()} ${staff_item.getUser().getSecondName()}</label></li>
                    </c:if>
                    </c:forEach>
                </ul>
            </div>
            </c:forEach>
        </div>

        <div id="change-div">
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherEditBrigadePageCommand" method="post">
                    <input type="hidden" name="brigade_id" value="${brigade.getId()}">
                    <input type="submit" value="<fmt:message key="brigade_info_dispatcher_jsp.link.change_data"/>" id="request-change-button">
                </form>
            </div>
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/controller?command=deleteDispatcherRequestCommand" method="post">
                    <input type="hidden" name="brigade_id" value="${brigade.getId()}">
                    <input type="submit" value="<fmt:message key="brigade_info_dispatcher_jsp.link.delete"/>" id="request-delete-button">
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

