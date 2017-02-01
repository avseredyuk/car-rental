<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.makeorder" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="leftmenu.jsp" %>
        <td width="100%">
            <table cellspacing="0" id="main">
                <tr>
                    <td id="main">
                        <br/>
                        <fmt:message key="makeorder.loginorregister" bundle="${bundle}"/><br/>
                        <br/>
                        <a href="${pageContext.request.contextPath}/?command=show_login"><fmt:message key="menu.login" bundle="${bundle}"/></a><br/>
                        <a href="${pageContext.request.contextPath}/?command=show_register"><fmt:message key="menu.register" bundle="${bundle}"/></a><br/>
                        <br/>
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td id="main">
                        <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step2" style="display: inline;">
                            <input type="submit" value="<fmt:message key="order.new.back" bundle="${bundle}"/>"/>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>