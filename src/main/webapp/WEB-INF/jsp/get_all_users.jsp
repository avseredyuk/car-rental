<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.users" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="leftmenu.jsp" %>
        <td width="100%">
            <div id="errorStatus" style="display:<c:choose><c:when test="${empty errorStatus}">none</c:when><c:otherwise>block</c:otherwise></c:choose>">
                <table cellspacing="0" id="content">
                    <tr>
                        <td>
                            <a href="javascript:toggle('errorStatus');"><fmt:message key="${errorStatus}" bundle="${bundle}"/></a>
                        </td>
                    </tr>
                </table>
            </div>
            <table cellspacing="0" id="content">
                <tr>
                    <th><fmt:message key="login.login" bundle="${bundle}"/><br/></th>
                    <th><fmt:message key="login.email" bundle="${bundle}"/></th>
                    <th><fmt:message key="login.name" bundle="${bundle}"/></th>
                    <th><fmt:message key="login.surname" bundle="${bundle}"/></th>
                    <th><fmt:message key="login.role" bundle="${bundle}"/></th>
                    <th><fmt:message key="users.registered" bundle="${bundle}"/></th>
                    <th><fmt:message key="actions" bundle="${bundle}"/></th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td id="rightcol">${fn:escapeXml(user.login)}</td>
                        <td id="rightcol">${fn:escapeXml(user.email)}</td>
                        <td id="rightcol">${fn:escapeXml(user.name)}</td>
                        <td id="rightcol">${fn:escapeXml(user.surname)}</td>
                        <td id="rightcol"><fmt:message key="client.type.${user.role}" bundle="${bundle}"/></td>
                        <td id="rightcol">
                            <fmt:formatDate pattern="DD.MM.YYYY HH:mm:ss" value="${user.registered}"/>
                        </td>
                        <td id="rightcol">
                            <a href="${pageContext.request.contextPath}/?command=get_user&user_id=${user.id}"><fmt:message key="action.edit" bundle="${bundle}"/></a>
                            <a href="${pageContext.request.contextPath}/?command=delete_user&user_id=${user.id}&return=${requestScope.requestURI}"><fmt:message key="action.delete" bundle="${bundle}"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <ctg:pagination command="${requestScope.command}" returnURI="${requestScope.requestURI}" pageInfo="${requestScope.paginationInformation}"/>
            <table cellspacing="0" id="content"><tr><td><a href="javascript:toggle('createForm');"><fmt:message key="users.createnew" bundle="${bundle}"/></a></td></tr></table>
            <div id="createForm" style="display: none">
                <table cellspacing="0" id="content">
                    <tr>
                        <td>
                            <form method="POST" action="${pageContext.request.contextPath}/" onsubmit="return validate_form('<fmt:message key="error.validation.form" bundle="${bundle}"/>');">
                                <input type="hidden" name="command" value="create_user"/>
                                <input type="hidden" name="return" value="${requestScope.requestURI}"/>
                                <fmt:message key="login.login" bundle="${bundle}"/>:<br/>
                                <input type="text" name="login" class="inputform" value=""/><br/>
                                <fmt:message key="login.email" bundle="${bundle}"/>:<br/>
                                <input type="text" name="email" class="inputform" value=""/><br/>
                                <fmt:message key="login.name" bundle="${bundle}"/>:<br/>
                                <input type="text" name="name" class="inputform" value=""/><br/>
                                <fmt:message key="login.surname" bundle="${bundle}"/>:<br/>
                                <input type="text" name="surname" class="inputform" value=""/><br/>
                                <fmt:message key="login.role" bundle="${bundle}"/>:<br/>
                                <select name="role">
                                    <c:forEach items="${rolesValues}" var="roleValue">
                                        <option value="${roleValue}"><fmt:message key="client.type.${roleValue}" bundle="${bundle}"/></option>
                                    </c:forEach>
                                </select><br/>
                                <fmt:message key="login.password" bundle="${bundle}"/>:<br/>
                                <input type="text" name="password" class="inputform" value=""/><br/>
                                <input type="submit" value="<fmt:message key="create" bundle="${bundle}"/>"/>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
</table>
</body>
</html>


