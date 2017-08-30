<%@ tag body-content="empty" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<jsp:useBean id="now" scope="application" class="java.util.Date" />
<p>Copyright &copy; ${now.year + 1900} <fmt:message key="header_jsp.title"/><fmt:message key="footer_jsp.footer"/></p>