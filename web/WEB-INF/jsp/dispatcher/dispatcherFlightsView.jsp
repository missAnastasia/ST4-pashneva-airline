<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Flights" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<jsp:include page="../_menu_dispatcher.jsp"/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="flights_dispatcher_jsp.h2"/></h2>
    </div>

    <div>
        <table>
            <tr>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.id"/></th>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.date"/></th>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.time"/></th>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.from"/></th>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.to"/></th>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.flight_status"/></th>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.brigade"/></th>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.aircruft"/></th>
            </tr>
            <div class="table-content">
            <tr>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
            </tr>
            </div>
            <%--<c:forEach items="${flights}" var="flight">
                <tr>
                    <td>${flight.id}</td>
                    <td>${flight.date}</td>
                    <td>${flight.date}</td>
                    <td>${flight.departurePoint}</td>
                    <td>${flight.arrivalPoint}</td>
                    <td>${flight.brigade.name}</td>
                    <td>${flight.flightStatus.getName()}</td>
                    <td>${flight.aircraftType}</td>
                </tr>
            </c:forEach>--%>
        </table>
    </div>
</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>
