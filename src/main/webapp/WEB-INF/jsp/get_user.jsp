<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.user.get" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="leftmenu.jsp" %>
        <td width="100%">
            <table cellspacing="0" id="content">
                <tr>
                    <td>
                        <div id="errorStatus" style="display:<c:choose><c:when test="${empty errorStatus}">none</c:when><c:otherwise>block</c:otherwise></c:choose>"><a href="javascript:toggle('errorStatus');"></a></div>
                        <form method="POST" action="${pageContext.request.contextPath}/" onsubmit="return validate_form('<fmt:message key="error.validation.form" bundle="${bundle}"/>');">
                            <input type="hidden" name="command" value="edit_user"/>
                            <input type="hidden" name="return" value="${requestScope.referer}"/>
                            <input type="hidden" name="user_id" value="${user.id}"/>
                            <fmt:message key="login.login" bundle="${bundle}"/>:<br/>
                            <input type="text" name="login" class="inputform" value="${fn:escapeXml(user.login)}"/><br/>
                            <fmt:message key="login.email" bundle="${bundle}"/>:<br/>
                            <input type="text" name="email" class="inputform" value="${fn:escapeXml(user.email)}"/><br/>
                            <fmt:message key="login.name" bundle="${bundle}"/>:<br/>
                            <input type="text" name="name" class="inputform" value="${fn:escapeXml(user.name)}"/><br/>
                            <fmt:message key="login.surname" bundle="${bundle}"/>:<br/>
                            <input type="text" name="surname" class="inputform" value="${fn:escapeXml(user.surname)}"/><br/>
                            <fmt:message key="users.registered" bundle="${bundle}"/><br/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.registered}"/><br/>
                            <fmt:message key="login.role" bundle="${bundle}"/>:<br/>
                            <select name="role">
                                <c:forEach items="${rolesValues}" var="roleValue">
                                    <option value="${roleValue}" <c:if test="${user.role == roleValue}">selected</c:if>><fmt:message key="client.type.${roleValue}" bundle="${bundle}"/></option>
                                </c:forEach>
                            </select><br/>
                            <fmt:message key="login.password" bundle="${bundle}"/>:<br/>
                            <%-- password field is empty --%>
                            <input type="text" name="password" value=""/><br/>
                            <div id="hint"><fmt:message key="hint.profile.password" bundle="${bundle}"/></div><br/>
                            <input type="submit" value="<fmt:message key="save" bundle="${bundle}"/>"/>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>
</html>


