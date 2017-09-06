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
        <h2><fmt:message key="flight_info_staff_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <div id="post-info-div">
            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.number"/>
                </label>
                <label class="label-v">${flight.getNumber()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.from"/>
                </label>
                <label class="label-v">${flight.getDeparturePoint()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.to"/>
                </label>
                <label class="label-v">${flight.getArrivalPoint()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.date"/>
                </label>
                <label class="label-v">${flight.getDate()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.time"/>
                </label>
                <label class="label-v">${flight.getTime()}</label>
            </div>

            <div class="one-post-info-div">
                <label class="label-n">
                    <fmt:message key="flight_info_staff_jsp.label.aircraft"/>
                </label>
                <label class="label-v">${flight.getAircraft().getTypeName()}</label>
            </div>

            <div class="one-post-info-div">
                    <label class="label-n">
                        <fmt:message key="flight_info_staff_jsp.label.brigade"/>
                    </label>
                    <label class="label-v">${flight.getBrigade().getNumber()}</label>
           </div>
            <div class="one-post-info-div">
                    <label class="label-n">
                        <fmt:message key="flight_info_staff_jsp.label.flight_status"/>
                    </label>
                <label class="label-v">${flight.getFlightStatus().getName()}</label>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>
