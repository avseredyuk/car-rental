<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.makeordererror" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="/WEB-INF/jsp/leftmenu.jsp" %>
        <td width="100%">
            <table cellspacing="0" id="main">
                <tr>
                    <td id="main">
                        <br/><fmt:message key="makeorder.order.error" bundle="${bundle}"/>: <fmt:message key="${requestScope.errorStatus}" bundle="${bundle}"/>
                        <br/>
                        <br/>
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td id="main">
                    <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step1" style="display: inline;">
                        <input type="submit" value="<fmt:message key="order.new.back" bundle="${bundle}"/>"/>
                    </form>
                    </td
                ></tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>


