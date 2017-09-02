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
                <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherFlightsPageCommand" autocomplete="on" method="post">
                    <select name="compare">
                        <option value="compare_by_number"><fmt:message key="flights_dispatcher_jsp.compare.by_number"/></option>
                        <option value="compare_by_departure_point"><fmt:message key="flights_dispatcher_jsp.compare.by_departure_point"/></option>
                        <option value="compare_by_arrival_point"><fmt:message key="flights_dispatcher_jsp.compare.by_arrival_point"/></option>
                    </select>
                    <input type="submit" value="<fmt:message key="flights_dispatcher_jsp.compare.submit"/>"/>
                </form>
            </div>

            <div id="items-div">
                <c:forEach items="${flights}" var="flight">
                    <form action="" autocomplete="on" method="post" id="flight-form">
                        <div class="item-div">
                            <table class="item-table">
                                <tr>
                                    <td colspan="3">
                                        <h3><button class="flight-number-link" type="submit" name="flight_number" value="${flight.getNumber()}" form="flight-form">${flight.getNumber()}</button></h3>
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
                    </form>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../_footer.jsp"/>

</body>
</html>
