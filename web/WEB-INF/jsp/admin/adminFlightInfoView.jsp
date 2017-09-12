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
        <h2><fmt:message key="flight_info_admin_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <div id="post-info-div">
            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.number"/>
                </label>
                <label class="label-v">${flight.getNumber()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.from"/>
                </label>
                <label class="label-v">${flight.getDeparturePoint()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.to"/>
                </label>
                <label class="label-v">${flight.getArrivalPoint()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.date"/>
                </label>
                <label class="label-v">${flight.getDate()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.time"/>
                </label>
                <label class="label-v">${flight.getTime()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.aircraft"/>
                </label>
                <label class="label-v">${flight.getAircraft().getTypeName()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.brigade"/>
                </label>
                <label class="label-v">
                    <c:choose>
                        <c:when test="${flight.getBrigade() != null}">
                            ${flight.getBrigade().getNumber()}
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="flight_info_admin_jsp.null_brigade"/>
                        </c:otherwise>
                    </c:choose>
                </label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_admin_jsp.label.flight_status"/>
                </label>
                <label class="label-v">${flight.getFlightStatus().getName()}</label>
            </div>
            <div id="change-div">
                <div class="change-a">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminEditFlightPageCommand" method="post">
                        <input type="hidden" name="flight_id" value="${flight.getId()}">
                        <input type="submit" value="<fmt:message key="flight_info_admin_jsp.link.change_data"/>" id="flight-change-button">
                    </form>
                </div>
                <div class="change-a">
                    <form action="${pageContext.request.contextPath}/controller?command=deleteAdminFlightCommand" method="post">
                        <input type="hidden" name="flight_id" value="${flight.getId()}">
                        <input type="submit" value="<fmt:message key="flight_info_admin_jsp.link.delete"/>" id="flight-delete-button">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>

