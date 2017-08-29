<%@ tag body-content="empty" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="now" scope="application" class="java.util.Date" />
<p>Copyright &copy; ${now.year + 1900} <fmt:message key="header_jsp.title"/><fmt:message key="footer_jsp.footer"/></p>