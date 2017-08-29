<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Home Page" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="WEB-INF/jsp/_header_client.jsp"></jsp:include>
<jsp:include page="WEB-INF/jsp/_menu_client.jsp"></jsp:include>

</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="settings_jsp.h2"/></h2>
    </div>
	<form action="controller?command=changeLocaleCommand" method="post">
        <h3><fmt:message key="settings_jsp.label.set_locale"/></h3>
		<select name="locale">
			<c:forEach items="${applicationScope.locales}" var="locale">
				<c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
				<option value="${locale.key}" ${selected}>${locale.value}</option>
			</c:forEach>
		</select>
        <br>
		<input type="submit" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">

	</form>
	</div>
<jsp:include page="WEB-INF/jsp/_footer.jsp"></jsp:include>
</body>
</html>