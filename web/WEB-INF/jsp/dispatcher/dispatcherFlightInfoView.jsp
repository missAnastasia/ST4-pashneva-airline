<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Flight Info" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<jsp:include page="../_menu_dispatcher.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="flights_dispatcher_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <div id="post-info-div">
            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.number"/>
                </label>
                <label class="label-v">${flight.getNumber()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.from"/>
                </label>
                <label class="label-v">${flight.getDeparturePoint()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.to"/>
                </label>
                <label class="label-v">${flight.getArrivalPoint()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.date"/>
                </label>
                <label class="label-v">${flight.getDate()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.time"/>
                </label>
                <label class="label-v">${flight.getTime()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.aircraft"/>
                </label>
                <label class="label-v">${flight.getAircraft().getTypeName()}</label>
            </div>

            <div class="one-post-info-div">
                <form action="${pageContext.request.contextPath}/controller?command=changeBrigadeCommand" method="post">
                    <label class="label-n">
                        <fmt:message key="flight_info_dispatcher_jsp.label.brigade"/>
                    </label>
                    <select name="brigade_id">
                        <c:forEach items="${brigades}" var="brigade_var">
                            <c:set var="selected" value="${brigade_var.getId() == flight.getBrigade().getId() ? 'selected' : '' }"/>
                            <option value="${brigade_var.getId()}" ${selected}>${brigade_var.getName()}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="flight_number" value="${flight.getNumber()}">
                    <input type="submit" value="<fmt:message key="flight_info_dispatcher_jsp.submit"/>">
                </form>


            </div>
            <div class="one-post-info-div">
                <form action="${pageContext.request.contextPath}/controller?command=changeFlightStatusCommand" method="post">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.flight_status"/>
                </label>
                <select name="flight_status_id">
                    <c:forEach items="${flight_statuses}" var="status">
                        <c:set var="selected" value="${status.getId() == flight.getFlightStatus().getId() ? 'selected' : '' }"/>
                        <option value="${status.getId()}" ${selected}>${status.getName()}</option>
                    </c:forEach>
                </select>
                    <input type="hidden" name="flight_number" value="${flight.getNumber()}">
                    <input type="submit" value="<fmt:message key="flight_info_dispatcher_jsp.submit"/>">
                </form>
            </div>
        </div>

        <%--<div id="change-div">
            <div class="change-a">
                <a href="${pageContext.request.contextPath}/redirect?command=getChangeUserDataPageCommand">
                    <fmt:message key="userInfo_jsp.link.change_data"/>
                </a>
            </div>
            <div class="change-a">
                <a href="${pageContext.request.contextPath}/redirect?command=getChangePasswordPageCommand">
                    <fmt:message key="userInfo_jsp.link.change_password"/>
                </a>
            </div>
        </div>--%>

    </div>

</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>

