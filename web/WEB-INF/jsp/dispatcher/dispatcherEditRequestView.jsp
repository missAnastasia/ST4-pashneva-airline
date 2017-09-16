<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Request Info" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<div id="wrapper">
    <jsp:include page="../_header.jsp"/>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="edit_request_dispatcher_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <form  action="${pageContext.request.contextPath}/controller?command=changeDispatcherRequestMessageCommand" autocomplete="on" method="post">
            <h3>
                <label for="message-textarea" class="message-textarea"><fmt:message key="edit_request_dispatcher_jsp.label.message"/></label><br>
            </h3>
            <textarea id="message-textarea" name="message" required minlength="1" maxlength="300" class="message">${request_item.getMessage()}</textarea><br>
            <input type="hidden" name="request_id" value="${request_item.getId()}">
            <input type="submit" class="full-width-button" value="<fmt:message key="edit_request_dispatcher_jsp.submit"/>"/>
        </form>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</div>
</body>
</html>
