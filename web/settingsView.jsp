<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>
<c:set var="title" value="Settings" />
<%@ include file="/WEB-INF/jspf/head_main.jspf" %>

<body>
<jsp:include page="WEB-INF/jsp/_header.jsp"/>
<cstm:menu_user_role/>
</div>
<div id="page" class="container">
    <div class="title">
        <h2><fmt:message key="settings_jsp.h2"/></h2>
    </div>
	<div class="main-content-div">
		<form action="${pageContext.request.contextPath}/controller?command=changeLocaleCommand" method="post">
			<h3><fmt:message key="settings_jsp.label.set_locale"/></h3>
			<select name="locale" class="search-select" id="bottom-margin-select">
				<c:forEach items="${applicationScope.locales}" var="locale">
					<c:set var="selected" value="${locale.key == currentLocale ? 'selected' : '' }"/>
					<option value="${locale.key}" ${selected}>${locale.value}</option>
				</c:forEach>
			</select>
			<br>
			<input type="submit" class="full-width-button" value="<fmt:message key='settings_jsp.form.submit_save_locale'/>">
		</form>
	</div>
	</div>
<jsp:include page="WEB-INF/jsp/_footer.jsp"/>
</body>
</html>