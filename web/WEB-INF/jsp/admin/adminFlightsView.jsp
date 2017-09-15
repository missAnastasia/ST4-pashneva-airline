<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Flights" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstmtf:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="flights_admin_jsp.h2"/></h2>
    </div>
    <div id="content-div">
        <div id="options-div">
            <div id="search-div">
                <div>
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminAddFlightPageCommand" autocomplete="on" method="post">
                        <input type="submit" class="full-width-button" value="<fmt:message key="flights_admin_jsp.add_flight"/>"/>
                    </form>
                </div>
                <div class="criterion_search-div">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminFlightsPageCommand" autocomplete="on" method="post">
                        <h3>
                            <label><fmt:message key="flights_admin_jsp.search.number"/></label><br>
                            <input class="full-input-field" id="number" name="flight_number" value="${flight_number}" minlength="4" maxlength="7" type="text" placeholder="XX-0000" pattern="([A-Z]{2})([-]{1})([0-9]{1,4})"/>
                            <span class="form__error"><fmt:message key="validation.flight_number"/></span>
                        </h3>
                        <input type="submit" class="full-width-button" value="<fmt:message key="flights_admin_jsp.search.submit"/>"/>
                    </form>
                </div>
                <div class="criterion_search-div">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminFlightsPageCommand" autocomplete="on" method="post">
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.flight_status"/></label><br>
                        </h3>
                            <select name="flight_status_id" class="search-select" id="item-select">
                                <c:forEach items="${statuses}" var="status">
                                    <c:set var="selected" value="${status.getId() == flight.getFlightStatus().getId() ? 'selected' : '' }"/>
                                    <option value="${status.getId()}" ${selected}>${status.getName()}</option>
                                </c:forEach>
                            </select>

                        <input type="submit" class="full-width-button" value="<fmt:message key="flights_admin_jsp.search.submit"/>"/>
                    </form>
                </div>
                <div class="criterion_search-div">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminFlightsPageCommand" autocomplete="on" method="post">
                        <h3>
                            <label><fmt:message key="flights_admin_jsp.search.from"/></label><br>
                            <input class="full-input-field" id="fromCity" name="from_city" value="${from_city}" minlength="1" maxlength="100" type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.departure_point"/></span>
                        </h3>
                        <h3>
                            <label><fmt:message key="flights_admin_jsp.search.to"/></label><br>
                            <input  class="full-input-field" id="toCity" name="to_city" value="${to_city}" minlength="1" maxlength="100" type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.arrival_point"/></span>
                        </h3>
                        <h3>
                            <label><fmt:message key="flights_admin_jsp.search.date"/></label><br>
                            <input  class="full-input-field" type="date" name="departure_date" value="${departure_date}"/>
                        </h3>
                        <input type="submit" class="full-width-button" value="<fmt:message key="flights_admin_jsp.search.submit"/>"/>
                    </form>
                </div>
            </div>
        </div>
        <div id="compare-div">
            <form action="${pageContext.request.contextPath}/redirect?command=getAdminFlightsPageCommand" method="post">
                <select name="compare" id="no-top-margin-select" class="search-select auto-width-select">
                    <c:forEach items="${compare_values.entrySet()}" var="compare_value">
                        <c:set var="selected" value="${compare_value.getKey() eq compare ? 'selected' : '' }"/>
                        <option value="${compare_value.getKey()}" ${selected}>${compare_value.getValue()}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="flight_number" value="${flight_number}">
                <input type="hidden" name="from_city" value="${from_city}">
                <input type="hidden" name="to_city" value="${to_city}">
                <input type="hidden" name="departure_date" value="${departure_date}">
                <input type="hidden" name="flight_status_id" value="${flight_status_id}">
                <input type="submit" id="compare-button" class="auto-width-button" value="<fmt:message key="flights_admin_jsp.compare.submit"/>"/>
            </form>
        </div>
        <div id="content-table-div">
            <div id="items-div">
                <c:forEach items="${flights}" var="flight">
                    <form action="${pageContext.request.contextPath}/redirect?command=getAdminFlightInfoPageCommand" method="post" id="flight-form">
                        <div class="item-div">
                            <table class="item-table">
                                <tr>
                                    <td colspan="3">
                                        <h3><button class="entity_info-link" type="submit" name="flight_id" value="${flight.getId()}" form="flight-form">${flight.getNumber()}</button></h3>
                                    </td>
                                    <td class="label-td"></td>
                                </tr>
                                <tr>
                                    <td class="destination-td">${flight.getDeparturePoint()}</td>
                                    <td class="separator-td">-</td>
                                    <td class="destination-td">${flight.getArrivalPoint()}</td>
                                    <td class="label-td"></td>
                                </tr>
                                <tr>
                                    <td colspan="3">${flight.getDate()}</td>
                                    <td class="label-td"></td>
                                </tr>
                                <tr>
                                    <td colspan="3">${flight.getTime()}</td>
                                    <td class="label-td"></td>
                                </tr>
                                <tr>
                                    <td colspan="3"><h4>${flight.getFlightStatus().getName()}</h4></td>
                                    <td class="label-td"></td>
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
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
