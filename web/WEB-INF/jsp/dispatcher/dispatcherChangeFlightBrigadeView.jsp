<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Flight Info" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="edit_flight_brigade_dispatcher_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <label class="label-n"><fmt:message key="edit_flight_brigade_dispatcher_jsp.label.number"/></label>
        <label class="label-v">${flight.getNumber()}</label>
        <form  action="controller?command=changeBrigadeCommand" autocomplete="on" method="post">
            <label class="label-n"><fmt:message key="edit_flight_brigade_dispatcher_jsp.label.brigade"/></label>
            <label class="label-v">
                <select name="new_brigade_id">
                    <c:forEach items="${brigades}" var="brigade">
                        <c:set var="selected" value="${brigade.getId() == flight.getBrigade().getId() ? 'selected' : '' }"/>
                        <option value="${brigade.getId()}" ${selected}>${brigade.getNumber()}</option>
                    </c:forEach>
                </select>
            </label><br>
            <input type="hidden" name="flight_id" value="${flight.getId()}">
            <input type="submit" class="auto-width-button" value="<fmt:message key="edit_flight_brigade_dispatcher_jsp.submit"/>"/>
        </form>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
