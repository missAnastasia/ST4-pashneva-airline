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
        <h2><fmt:message key="edit_flight_admin_jsp.h2"/></h2>
    </div>

    <div class="login-div" id="user-info-div">
        <form  action="controller?command=editAdminFlightCommand" autocomplete="on" method="post">
            <h3>
                <label for="flight_number" class="label-n"><fmt:message key="edit_flight_admin_jsp.label.number"/></label><br>
                <input id="flight_number" name="flight_number" value="${flight.getNumber()}" minlength="4" maxlength="7" required type="text" placeholder="XX-0000" pattern="^[A-Z]{2}[-][0-9]{1,4}$"/>
                <span class="form__error"><fmt:message key="validation.flight_number"/></span>
            </h3>
            <h3>
                <label for="date_time" class="label-n"><fmt:message key="edit_flight_admin_jsp.label.date_time"/></label><br>
                <input id="date_time" name="date_time" type="datetime-local" required value="${flight.getNumber()}"/>
            </h3>


            <h3>
                <label class="label-n"><fmt:message key="edit_flight_admin_jsp.label.aircraft"/></label><br>
                <select name="aircraft_id" class="aircraft-select" required>
                    <c:forEach items="${aircraft}" var="aircraft_item">
                        <c:set var="selected" value="${aircraft.getId() == flight.getAircraft().getId() ? 'selected' : '' }"/>
                        <option value="${aircraft_item.getId()}" ${selected}>${aircraft_item.getTypeName()}</option>
                    </c:forEach>
                </select>
            </h3>

            <div class="lang-item-div">
                <h3 id="label-langs">
                    <label><fmt:message key="edit_flight_admin_jsp.label.languages"/></label><br>
                </h3>
                <c:forEach items="${languages}" var="language">
                    <label class="label-lang-name">${language.getName()}:</label><br>
                    <div class="item-div">
                        <h3>
                            <label class="label-n"><fmt:message key="edit_flight_admin_jsp.label.date_time"/></label><br>
                            <input name="${language.getPrefix()}_from" type="text" required value="${flight.getDeparturePoint()}" minlength="1" maxlength="100" type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.departure_point"/></span>
                        </h3>


                        <h3>
                            <label class="label-n"><fmt:message key="edit_flight_admin_jsp.label.date_time"/></label><br>
                            <input name="${language.getPrefix()}_to" type="text" required value="${flight.getArrivalPoint()}" minlength="1" maxlength="100" type="text" placeholder="Xxx" pattern="([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+)((\u0020?)([a-zA-Z\u0430-\u044f\u0410-\u042f\u0401\u0451]+))*"/>
                            <span class="form__error"><fmt:message key="validation.arrival_point"/></span>
                        </h3>
                    </div>
                </c:forEach>


            </div>
            <input type="submit" value="<fmt:message key="edit_flight_admin_jsp.submit"/>"  id="flight-save-button"/>
        </form>
    </div>
</div>
<jsp:include page="../_footer.jsp"/>
</body>
</html>
