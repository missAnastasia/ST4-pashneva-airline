<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Brigades" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="../_header.jsp"/>
<cstm:menu_user_role/>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="brigades_dispatcher_jsp.h2"/></h2>
    </div>

    <div id="content-div">
        <%--<form action="${pageContext.request.contextPath}/redirect?command=getDispatcherFlightsPageCommand" autocomplete="on" method="post">--%>
        <div id="options-div">
            <div id="search-div" class="login-div">
                <div class="criterion_search-div">
                    <form action="<%--${pageContext.request.contextPath}/redirect?command=getDispatcherFlightsPageCommand--%>" autocomplete="on" method="post">
                        <input type="submit" value="<fmt:message key="brigades_dispatcher_jsp.add_brigade"/>"/>
                    </form>
                </div>

                <%--<div class="criterion_search-div">
                    <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherFlightsPageCommand" autocomplete="on" method="post">
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.from"/></label><br>
                            <input class="field-form" id="fromCity" name="from_city" value="${from_city}" minlength="1" maxlength="100" type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.departure_point"/></span>
                        </h3>
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.to"/></label><br>
                            <input  class="field-form" id="toCity" name="to_city" value="${to_city}" minlength="1" maxlength="100" type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.arrival_point"/></span>
                        </h3>
                        <h3>
                            <label><fmt:message key="flights_dispatcher_jsp.search.date"/></label><br>
                            <input  class="field-form" type="date" name="departure_date" value="${departure_date}"/>
                        </h3>
                        <input type="submit" value="<fmt:message key="flights_dispatcher_jsp.search.submit"/>"/>
                    </form>
                </div>--%>
            </div>
        </div>
        <%--<div id="compare-div">
            <form action="${pageContext.request.contextPath}/redirect?command=getDispatcherFlightsPageCommand" method="post">
                &lt;%&ndash;<c:if test="${not empty requestScope.number}">
                     <c:set var="number" value="${requestScope.number}" scope="request"/>
                 </c:if>
                 <c:if test="${not empty requestScope.from_city}">
                     <c:set var="from_city" value="${requestScope.from_city}" scope="request"/>
                 </c:if>
                 <c:if test="${not empty requestScope.to_city}">
                     <c:set var="to_city" value="${requestScope.to_city}" scope="request"/>
                 </c:if>
                 <c:if test="${not empty requestScope.departure_date}">
                     <c:set var="departure_date" value="${requestScope.departure_date}" scope="request"/>
                 </c:if>&ndash;%&gt;
                <select name="compare">
                    <option value="no_compare">-</option>
                    <option value="compare_by_number"><fmt:message key="flights_dispatcher_jsp.compare.by_number"/></option>
                    <option value="compare_by_departure_point"><fmt:message key="flights_dispatcher_jsp.compare.by_departure_point"/></option>
                    <option value="compare_by_arrival_point"><fmt:message key="flights_dispatcher_jsp.compare.by_arrival_point"/></option>
                </select>
                <input type="hidden" name="flight_number" value="${flight_number}">
                <input type="hidden" name="from_city" value="${from_city}">
                <input type="hidden" name="to_city" value="${to_city}">
                <input type="hidden" name="departure_date" value="${departure_date}">
                <input type="submit" value="<fmt:message key="flights_dispatcher_jsp.compare.submit"/>"/>
            </form>
        </div>--%>
        <%--</form>--%>
        <div id="content-table-div">
            <div id="items-div">
                <c:forEach items="${brigades}" var="brigade">
                    <form action="<%--${pageContext.request.contextPath}/redirect?command=getDispatcherFlightInfoPageCommand--%>" method="post" id="brigade-form">
                        <div class="item-div">
                            <table class="item-table">
                                <tr>
                                    <td colspan="2">
                                        <h3><button class="brigade-name-link" type="submit" name="brigade_name" value="${brigade.getNumber()}" form="brigade-form">${brigade.getNumber()}</button></h3>
                                    </td>
                                </tr>
                                <c:forEach items="${brigade.getStaff()}" var="staff">
                                    <tr>
                                        <td class="staff-name-td">${staff.getUser().getFirstName()} ${staff.getUser().getSecondName()}</td>
                                        <td class="staff-post-td">${staff.getPost().getName()}</td>
                                    </tr>
                                </c:forEach>
                                <%--<tr>
                                    <td class="destination-td">${flight.getDeparturePoint()}</td>
                                    <td class="separator-td">-</td>
                                    <td class="destination-td">${flight.getArrivalPoint()}</td>
                                </tr>
                                <tr>
                                    <td colspan="3">${flight.getDate()}</td>
                                </tr>
                                <tr>
                                    <td colspan="3">${flight.getTime()}</td>
                                </tr>
                                <tr>
                                    <td colspan="3">${flight.getFlightStatus().getName()}</td>
                                </tr>--%>
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
