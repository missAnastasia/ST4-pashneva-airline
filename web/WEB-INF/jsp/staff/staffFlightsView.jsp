<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Flights" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
    <div id="wrapper">
        <jsp:include page="../_header.jsp"/>
        <div id="page" class="container">
            <div class="title">
                <h2><fmt:message key="flights_dispatcher_jsp.h2"/></h2>
            </div>
            <div id="content-div">
                <div id="items-div">
                    <c:forEach items="${flights}" var="flight">
                        <form action="${pageContext.request.contextPath}/redirect?command=getStaffFlightInfoPageCommand" method="post" id="flight-form">
                            <div class="item-div">
                                <table class="item-table">
                                    <tr>
                                        <td class="label-td"><h3><fmt:message key="flights_staff_jsp.table.th.number"/></h3></td>
                                        <td colspan="3">
                                            <h3><button class="entity_info-link" type="submit" name="flight_id" value="${flight.getId()}" form="flight-form">${flight.getNumber()}</button></h3>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="label-td"><h3><fmt:message key="flights_staff_jsp.table.th.from"/> - <fmt:message key="flights_staff_jsp.table.th.to"/></h3></td>
                                        <td class="destination-td">${flight.getDeparturePoint()}</td>
                                        <td class="separator-td">-</td>
                                        <td class="destination-td">${flight.getArrivalPoint()}</td>
                                    </tr>
                                    <tr>
                                        <td class="label-td"><h3><fmt:message key="flights_staff_jsp.table.th.date"/></h3></td>
                                        <td colspan="3">${flight.getDate()}</td>
                                    </tr>
                                    <tr>
                                        <td class="label-td"><h3><fmt:message key="flights_staff_jsp.table.th.time"/></h3></td>
                                        <td colspan="3">${flight.getTime()}</td>
                                    </tr>
                                    <tr>
                                        <td class="label-td"><h3><fmt:message key="flights_staff_jsp.table.th.flight_status"/></h3></td>
                                        <td colspan="3"><h4>${flight.getFlightStatus().getName()}</h4></td>
                                    </tr>
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
        <jsp:include page="../_footer.jsp"/>
    </div>
</body>
</html>
