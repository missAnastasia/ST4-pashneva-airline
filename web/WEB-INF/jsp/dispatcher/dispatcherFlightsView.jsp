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

    <div id="content-div">
        <div id="options-div">
            <div id="search-div" class="login-div">
                <div class="criterion_search-div">
                    <form action="" autocomplete="on" method="post">
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.number"/></label><br>
                            <input class="field-number" id="number" name="number" minlength="4" maxlength="7" required type="text" placeholder="XX-0000" pattern="([A-Z]{2})([-]{1})([0-9]{1,4})"/>
                            <span class="form__error"><fmt:message key="validation.flight_number"/></span>
                        </h3>
                        <input type="submit" value="<fmt:message key="flights_dispatcher_jsp.search.submit"/>"/>
                    </form>
                </div>

                <div class="criterion_search-div">
                    <form action="" autocomplete="on" method="post" id="f">
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.from"/></label><br>
                            <input class="field-form" id="fromCity" name="fromCity" minlength="1" maxlength="100" required type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.departure_point"/></span>
                        </h3>
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.to"/></label><br>
                            <input  class="field-form" id="toCity" name="toCity" minlength="1" maxlength="100" required type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.arrival_point"/></span>
                        </h3>
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.date"/></label><br>
                            <input  class="field-form" type="date" />
                        </h3>
                        <input type="submit" value="<fmt:message key="flights_dispatcher_jsp.search.submit"/>"/>
                    </form>
                </div>
            </div>
        </div>
        <div id="content-table-div">

            <div id="compare-div">
                <select name="compare">
                    <option value="compare_by_number"><fmt:message key="flights_dispatcher_jsp.compare.by_number"/></option>
                    <option value="compare_by_departure_point"><fmt:message key="flights_dispatcher_jsp.compare.by_departure_point"/></option>
                    <option value="compare_by_arrival_point"><fmt:message key="flights_dispatcher_jsp.compare.by_arrival_point"/></option>
                </select>
                <input type="submit" value="<fmt:message key="flights_dispatcher_jsp.compare.submit"/>"/>
            </div>

            <div id="items-div">
                <c:forEach items="${flights}" var="flight">
                    <div class="item-div">
                        <table class="item-table">
                            <tr>
                                <td colspan="3">
                                    <h3><a href="#">${flight.getNumber()}</a></h3>
                                </td>
                            </tr>
                            <tr>
                                <td class="destination-td">${flight.getDeparturePoint()}</td>
                                <td class="separator-td">-</td>
                                <td class="destination-td">${flight.getArrivalPoint()}</td>
                            </tr>
                            <tr>
                                <td colspan="3">${flight.getDate()}</td>
                            </tr>
                        </table>
                    </div>
                </c:forEach>
            </div>

        </div>
        <%--<table>
            <tr>
                <th><fmt:message key="flights_dispatcher_jsp.table.th.number"/></th>
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
            </div>--%>
            <%--<c:forEach items="${flights}" var="flight">
                <tr>
                    <td>${flight.number}</td>
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
