<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Add Request" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>

<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="add_request_dispatcher_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <form  action="controller?command=addDispatcherRequestCommand" autocomplete="on" method="post">
            <h3>
                <label for="message-textarea" class="message-textarea"><fmt:message key="add_request_dispatcher_jsp.label.message"/></label><br>
            </h3>
            <textarea id="message-textarea" name="message" required minlength="1" maxlength="300" class="message"></textarea><br>
            <input type="hidden" name="request_id">
            <input type="submit" value="<fmt:message key="add_request_dispatcher_jsp.submit"/>"/>



            <%--<p class="change_link">
                <fmt:message key="register_jsp.label.already_have_account"/>
                <a href="controller?command=getLoginPageCommand" class="to_register"><fmt:message key="register_jsp.log_in"/></a>
            </p>--%>
        </form>
    </div>

</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>
