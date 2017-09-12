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
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.brigade"/>
                </label>
                <label class="label-v">
                    <c:choose>
                        <c:when test="${flight.getBrigade() != null}">
                            ${flight.getBrigade().getNumber()}
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="flight_info_dispatcher_jsp.null_brigade"/>
                        </c:otherwise>
                    </c:choose>
                </label>
                <form action="${pageContext.request.contextPath}/controller?command=changeBrigadeCommand" method="post">
                <input type="hidden" name="flight_id" value="${flight.getId()}">
                <input type="submit" class="flight-change-button" value="<fmt:message key="flight_info_dispatcher_jsp.submit"/>">
                </form>
                    <%--<form action="${pageContext.request.contextPath}/controller?command=changeBrigadeCommand" method="post">
                    <label class="label-n">
                        <fmt:message key="flight_info_dispatcher_jsp.label.brigade"/>
                    </label>
                    <select name="brigade_id" class="flight-select">
                        <c:forEach items="${brigades}" var="brigade_var">
                            <c:set var="selected" value="${brigade_var.getId() == flight.getBrigade().getId() ? 'selected' : '' }"/>
                            <option value="${brigade_var.getId()}" ${selected}>${brigade_var.getName()}</option>
                        </c:forEach>
                        <c:if test="${flight.getBrigade() == null}">
                        </c:if>
                    </select><br>
                    <input type="hidden" name="flight_number" value="${flight.getNumber()}">
                    <input type="submit" class="flight-change-button" value="<fmt:message key="flight_info_dispatcher_jsp.submit"/>">
                </form>--%>


            </div>
            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.flight_status"/>
                </label>
                <label class="label-v">${flight.getFlightStatus().getName()}</label>
                <form action="${pageContext.request.contextPath}/controller?command=changeFlightStatusCommand" method="post">
                <input type="hidden" name="flight_id" value="${flight.getId()}">
                <input type="submit" class="flight-change-button" value="<fmt:message key="flight_info_dispatcher_jsp.submit"/>">
                </form>

                <%--<form action="${pageContext.request.contextPath}/controller?command=changeFlightStatusCommand" method="post">
                <label class="label-n">
                    <fmt:message key="flight_info_dispatcher_jsp.label.flight_status"/>
                </label>
                <select name="flight_status_id" class="flight-select">
                    <c:forEach items="${flight_statuses}" var="status">
                        <c:set var="selected" value="${status.getId() == flight.getFlightStatus().getId() ? 'selected' : '' }"/>
                        <option value="${status.getId()}" ${selected}>${status.getName()}</option>
                    </c:forEach>
                </select><br>
                    <input type="hidden" name="flight_number" value="${flight.getNumber()}">
                    <input type="submit" class="flight-change-button" value="<fmt:message key="flight_info_dispatcher_jsp.submit"/>">
                </form>--%>
            </div>
        </div>

        <%--<div id="change-div">
            <div class="change-a">
                <form action="${pageContext.request.contextPath}/redirect?command=getAdminEditFlightPageCommand" method="post">
                    <input type="hidden" name="flight_id" value="${flight.getId()}">
                    <input type="submit" value="<fmt:message key="flight_info_admin_jsp.link.change_data"/>" id="flight-change-button">
                </form>
            </div>
        </div>--%>

    </div>

</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>

