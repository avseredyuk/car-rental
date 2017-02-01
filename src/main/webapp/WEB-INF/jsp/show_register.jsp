<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.register" bundle="${bundle}"/></title>
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
                        <div id="errorStatus" style="display:<c:choose><c:when test="${empty errorStatus}">none</c:when><c:otherwise>block</c:otherwise></c:choose>"><a href="javascript:toggle('errorStatus');"><fmt:message key="${errorStatus}" bundle="${bundle}"/></a></div>
                        <form method="POST" action="${pageContext.request.contextPath}/" onsubmit="return validate_form('<fmt:message key="error.validation.form" bundle="${bundle}"/>');">
                            <input type="hidden" name="command" value="register"/>
                            <input type="hidden" name="return" value="${requestScope.referer}"/>
                            <fmt:message key="login.login" bundle="${bundle}"/>:<br/>
                            <input type="text" name="login" class="inputform" value=""/><br/>
                            <fmt:message key="login.email" bundle="${bundle}"/>:<br/>
                            <input type="text" name="email" class="inputform" value=""/><br/>
                            <fmt:message key="login.name" bundle="${bundle}"/>:<br/>
                            <input type="text" name="name" class="inputform" value=""/><br/>
                            <fmt:message key="login.surname" bundle="${bundle}"/>:<br/>
                            <input type="text" name="surname" class="inputform" value=""/><br/>
                            <fmt:message key="login.password" bundle="${bundle}"/>:<br/>
                            <input type="text" name="password" class="inputform" value=""/><br/><br/>
                            <input type="submit" value="<fmt:message key="login.register" bundle="${bundle}"/>"/>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>


