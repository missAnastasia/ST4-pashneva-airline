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
                <h2><fmt:message key="request_info_admin_jsp.h2"/></h2>
            </div>
            <div class="main-content-div" id="info-div">
                <div id="properties-div">
                    <div class="one-property-div">
                        <label class="label-n">
                            <fmt:message key="request_info_admin_jsp.label.number"/>
                        </label>
                        <label class="label-v">${request_item.getNumber()}</label>
                    </div>
                    <div class="one-property-div">
                        <label class="label-n">
                            <fmt:message key="request_info_admin_jsp.label.user"/>
                        </label>
                        <label class="label-v">${request_item.getUser().getLogin()} (${request_item.getUser().getFirstName()} ${request_item.getUser().getSecondName()})</label>
                    </div>
                    <div class="one-property-div">
                        <label class="label-n">
                            <fmt:message key="request_info_admin_jsp.label.date"/>
                        </label>
                        <label class="label-v">${request_item.getDate()}</label>
                    </div>
                    <div class="one-property-div">
                        <label class="label-n">
                            <fmt:message key="request_info_admin_jsp.label.message"/>
                        </label>
                        <label class="label-v">${request_item.getMessage()}</label>
                    </div>
                </div>
                <div id="change-div">
                    <div class="change-a">
                        <form action="${pageContext.request.contextPath}/controller?command=changeRequestStatusCommand" method="post">
                            <label class="label-n">
                                <fmt:message key="request_info_admin_jsp.label.request_status"/>
                            </label>
                            <select name="status_id" class="entity-select">
                                <c:forEach items="${statuses}" var="status_var">
                                    <c:set var="selected" value="${status_var.getId() == request_item.getRequestStatus().getId() ? 'selected' : '' }"/>
                                    <option value="${status_var.getId()}" ${selected}>${status_var.getName()}</option>
                                </c:forEach>
                            </select><br>
                            <input type="hidden" name="request_id" value="${request_item.getId()}">
                            <input type="submit" class="auto-width-button" value="<fmt:message key="request_info_admin_jsp.submit"/>">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="../_footer.jsp"/>
    </div>
</body>
</html>

