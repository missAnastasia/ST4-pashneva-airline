<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Brigade Info"/>
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstmtf:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="edit_brigade_dispatcher_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <form action="${pageContext.request.contextPath}/controller?command=editDispatcherBrigadeCommand" autocomplete="on" method="post">
            <h3>
                <label class="number"><fmt:message key="edit_brigade_dispatcher_jsp.number"/></label><br>
                <input class="full-input-field" name="brigade_number" value="${brigade.getNumber()}" minlength="4" maxlength="6"
                       required type="text" placeholder="X-0000" pattern="^[A-Z]{1}[-][0-9]{1,4}$"/>
                <span class="form__error"><fmt:message key="validation.brigade_number"/></span>
            </h3>
            <div id="properties-div">
                <h3 id="staff-list-h3"><fmt:message key="edit_brigade_dispatcher_jsp.staff_list"/></h3>
                <c:forEach items="${staff.entrySet()}" var="staff_item">
                <div class="one-property-div">
                    <label class="label-n">${staff_item.getKey().getName()}:</label>
                    <ul class="staff-add-list">
                        <c:choose>
                            <c:when test="${staff_item.getValue().size() > 0}">
                                <c:forEach items="${staff_item.getValue()}" var="staff_list_item">
                                    <c:set var="checked"
                                           value="${brigade.getStaff().contains(staff_list_item) ? 'checked' : '' }"/>
                                    <li class="staff-list-item">
                                        <input type="checkbox" ${checked} name="staff_id"
                                               value="${staff_list_item.getId()}"><label
                                            class="label-v">${staff_list_item.getUser().getFirstName()} ${staff_list_item.getUser().getSecondName()}</label>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <label class="label-v"><label class="warning"><fmt:message
                                        key="edit_brigade_dispatcher_jsp.no_staff"/></label></label>
                                <c:set var="no_staff" value="true" scope="page"/>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                </c:forEach>
                <c:choose>
                <c:when test="${no_staff eq 'true'}">
        </form>
        <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherAddRequestPageCommand" method="post">
            <input type="submit" class="full-width-button" value="<fmt:message key="edit_brigade_dispatcher_jsp.request_to_admin"/>">
        </form>
    </div>
    </c:when>
    <c:otherwise>
    <input type="hidden" name="brigade_id" value="${brigade.getId()}">
    <input type="submit" class="full-width-button" value="<fmt:message key="edit_brigade_dispatcher_jsp.submit"/>">
</div>
</form>
</c:otherwise>
</c:choose>
</div>
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
