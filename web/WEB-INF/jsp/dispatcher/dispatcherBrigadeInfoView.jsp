<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Brigade Info" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="brigade_info_dispatcher_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <div id="properties-div">
                <label class="label-n">
                    <fmt:message key="brigade_info_dispatcher_jsp.number"/>
                </label>
                <label class="label-v">${brigade.getNumber()}</label>
            <h3 id="staff-list-h3"><fmt:message key="brigade_info_dispatcher_jsp.staff_list"/></h3>
            <c:forEach items="${staff.entrySet()}" var="staff_item">
                <div class="one-property-div">
                    <label class="label-n">${staff_item.getKey().getName()}:</label>
                    <ul class="staff-list">
                        <c:choose>
                            <c:when test="${staff_item.getValue().size() > 0}">
                                <c:forEach items="${staff_item.getValue()}" var="staff_list_item">
                                    <li class="staff-list-item">
                                        <label class="label-v">${staff_list_item.getUser().getFirstName()} ${staff_list_item.getUser().getSecondName()}</label>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <label class="label-v"><fmt:message key="add_brigade_dispatcher_jsp.no_staff"/></label>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </c:forEach>
        </div>
        <div id="change-div">
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherEditBrigadePageCommand" method="post">
                    <input type="hidden" name="brigade_id" value="${brigade.getId()}">
                    <input type="submit" class="auto-width-button" value="<fmt:message key="brigade_info_dispatcher_jsp.link.change_data"/>">
                </form>
            </div>
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/controller?command=deleteDispatcherBrigadeCommand" method="post">
                    <input type="hidden" name="brigade_id" value="${brigade.getId()}">
                    <input type="submit" value="<fmt:message key="brigade_info_dispatcher_jsp.link.delete"/>" id="entity-delete-button">
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>

