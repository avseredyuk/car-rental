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
            <table cellspacing="0" id="content">
                <tr>
                    <td>
                        <br/>
                        <fmt:message key="makeorder.order.success" bundle="${bundle}"/>
                        <br/><br/>
                        <fmt:message key="makeorder.order.vieworder" bundle="${bundle}"/> <a href="${pageContext.request.contextPath}/?command=show_profile"><fmt:message key="menu.profile" bundle="${bundle}"/></a><br/>
                        <br/>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>