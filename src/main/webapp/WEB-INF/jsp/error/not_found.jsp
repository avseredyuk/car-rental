<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.error" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="/WEB-INF/jsp/leftmenu.jsp" %>
        <td width="100%">
            <table cellspacing="0" id="content">
                <tr><td><fmt:message key="errors.notfound" bundle="${bundle}"/></td></tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>


