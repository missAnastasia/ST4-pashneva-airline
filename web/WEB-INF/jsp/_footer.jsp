<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<div id="portfolio-wrapper">
    <div id="portfolio" class="container">
        <div class="title">
            <h3><fmt:message key="footer_jsp.title"/></h3>
            <br>
            <div class="column1">
                <div class="box">
                    <p><fmt:message key="footer_jsp.column1.address"/></p>
                    <p>+38(012)-345-6789</p>
                    <ul class="contact">
                        <li><a href="https://twitter.com" class="icon icon-twitter"><span>Twitter</span></a></li>
                        <li><a href="https://www.facebook.com" class="icon icon-facebook"><span>Facebook</span></a></li>
                    </ul>
                </div>
            </div>
            <div class="column4">
                <div class="box">
                    <div class="lang-form">
                    <a id="lang-link" href="${pageContext.request.contextPath}/settingsView.jsp">
                        <img id="lang-img" src="${pageContext.request.contextPath}/resources/style/images/1996.png" width="50"/>
                    </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <cstm:copyright/>
    </div>
</div>
</div>