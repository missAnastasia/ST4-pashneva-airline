<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="New Request" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="add_request_dispatcher_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <form  action="${pageContext.request.contextPath}/controller?command=addDispatcherRequestCommand" autocomplete="on" method="post">
            <h3>
                <label for="message-textarea" class="message-textarea"><fmt:message key="add_request_dispatcher_jsp.label.message"/></label><br>
            </h3>
            <textarea id="message-textarea" name="message" required minlength="1" maxlength="300" class="message"></textarea><br>
            <input type="submit" class="full-width-button" value="<fmt:message key="add_request_dispatcher_jsp.submit"/>"/>
        </form>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
