<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Conditions Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<jsp:include page="../_menu_staff.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="flights_staff_jsp.h2"/></h2>
    </div>

    <div>
        <table>
            <th>
                <td><fmt:message key="flights_staff_jsp.table.th.id"/></td>
                <td><fmt:message key="flights_staff_jsp.table.th.date"/></td>
                <td><fmt:message key="flights_staff_jsp.table.th.time"/></td>
                <td><fmt:message key="flights_staff_jsp.table.th.from"/></td>
                <td><fmt:message key="flights_staff_jsp.table.th.to"/></td>
                <td><fmt:message key="flights_staff_jsp.table.th.flight_status"/></td>
                <td><fmt:message key="flights_staff_jsp.table.th.brigade"/></td>
                <td><fmt:message key="flights_staff_jsp.table.th.aircruft"/></td>
            </th>
            <c:forEach items="${flights}" var="flight">
                <th>
                    <td>${flight.id}</td>
                    <td>${flight.date}</td>
                    <td>${flight.date}</td>
                    <td>${flight.departurePoint}</td>
                    <td>${flight.arrivalPoint}</td>
                    <td>${flight.brigade.name}</td>
                    <td>${flight.flightStatus.getName()}</td>
                    <td>${flight.aircraftType}</td>
                </th>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>
