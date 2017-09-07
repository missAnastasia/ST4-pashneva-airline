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
        <h2><fmt:message key="request_info_dispatcher_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <div id="post-info-div">
            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="request_info_dispatcher_jsp.label.number"/>
                </label>
                <label class="label-v">${request_item.getNumber()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="request_info_dispatcher_jsp.label.request_status"/>
                </label>
                <label class="label-v">${request_item.getRequestStatus().getName()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="request_info_dispatcher_jsp.label.message"/>
                </label>
                <label class="label-v">${request_item.getMessage()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="request_info_dispatcher_jsp.label.date"/>
                </label>
                <label class="label-v">${request_item.getDate()}</label>
            </div>
        </div>

        <div id="change-div">
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherEditRequestPageCommand" method="post">
                    <input type="hidden" name="request_id" value="${request_item.getId()}">
                    <input type="submit" value="<fmt:message key="request_info_dispatcher_jsp.link.change_data"/>" id="request-change-button">
                </form>
            </div>
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/controller?command=deleteDispatcherRequestCommand" method="post">
                    <input type="hidden" name="request_id" value="${request_item.getId()}">
                    <input type="submit" value="<fmt:message key="request_info_dispatcher_jsp.link.delete"/>" id="request-delete-button">
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

