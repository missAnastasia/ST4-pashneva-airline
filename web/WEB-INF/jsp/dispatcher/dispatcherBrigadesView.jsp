<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Brigades" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstmtf:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="brigades_dispatcher_jsp.h2"/></h2>
    </div>
    <div id="content-div">
        <div id="options-div">
            <div id="search-div">
                <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherAddBrigadePageCommand" autocomplete="on" method="post">
                    <input type="submit" class="full-width-button" value="<fmt:message key="brigades_dispatcher_jsp.add_brigade"/>"/>
                </form>
            </div>
        </div>
        <div id="content-table-div">
            <div id="items-div">
                <c:forEach items="${brigades}" var="brigade">
                    <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherBrigadeInfoPageCommand" method="post" id="brigade-form">
                        <div class="item-div">
                            <table class="item-table">
                                <tr>
                                    <td colspan="2">
                                        <h3><button class="entity_info-link" type="submit" name="brigade_id" value="${brigade.getId()}" form="brigade-form">${brigade.getNumber()}</button></h3>
                                    </td>
                                </tr>
                                <c:choose>
                                    <c:when test="${brigade.getStaff().size() > 0}">
                                        <c:forEach items="${brigade.getStaff()}" var="staff">
                                            <tr>
                                                <td class="item-left-td">${staff.getUser().getFirstName()} ${staff.getUser().getSecondName()}</td>
                                                <td class="item-right-td">${staff.getPost().getName()}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <td class="item-left-td">
                                                <label class="warning"><fmt:message key="brigades_dispatcher_jsp.no_staff"/></label>
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
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
<jsp:include page="../_footer.jsp"/>
</body>
</html>
