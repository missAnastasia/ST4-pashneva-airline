<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Flight Info"/>
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<div id="wrapper">
    <jsp:include page="_header.jsp"/>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="flight_info_staff_jsp.h2"/></h2>
    </div>
    <div class="main-content-div" id="info-div">
        <div id="properties-div">
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.number"/>
                </label>
                <label class="label-v">${flight.getNumber()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.from"/>
                </label>
                <label class="label-v">${flight.getDeparturePoint()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.to"/>
                </label>
                <label class="label-v">${flight.getArrivalPoint()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.date"/>
                </label>
                <label class="label-v">${flight.getDate()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.time"/>
                </label>
                <label class="label-v">${flight.getTime()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.aircraft"/>
                </label>
                <label class="label-v">${flight.getAircraft().getTypeName()}</label>
            </div>
            <div class="one-property-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.brigade"/>
                </label>
                <label class="label-v">${flight.getBrigade().getNumber()}</label>
           </div>
            <div class="one-property-div">
                    <label class="label-n">
                        <fmt:message key="flight_info_staff_jsp.label.flight_status"/>
                    </label>
                <label class="label-v">${flight.getFlightStatus().getName()}</label>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</div>
</body>
</html>

