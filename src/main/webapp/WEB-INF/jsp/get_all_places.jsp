<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.places" bundle="${bundle}"/></title>
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
                    <th><fmt:message key="places.name" bundle="${bundle}"/></th>
                    <th><fmt:message key="places.address" bundle="${bundle}"/></th>
                    <th><fmt:message key="places.type" bundle="${bundle}"/></th>
                    <th><fmt:message key="actions" bundle="${bundle}"/></th>
                </tr>
                <c:forEach items="${places}" var="place">
                    <tr>
                        <td id="rightcol">${fn:escapeXml(place.name)}</td>
                        <td id="rightcol">${fn:escapeXml(place.address)}</td>
                        <td id="rightcol"><fmt:message key="place.type.${place.type}" bundle="${bundle}"/></td>
                        <td id="rightcol">
                            <a href="${pageContext.request.contextPath}/?command=get_place&place_id=${place.id}"><fmt:message key="action.edit" bundle="${bundle}"/></a>
                            <a href="${pageContext.request.contextPath}/?command=delete_place&place_id=${place.id}&return=${requestScope.requestURI}"><fmt:message key="action.delete" bundle="${bundle}"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <ctg:pagination command="${requestScope.command}" returnURI="${requestScope.requestURI}" pageInfo="${requestScope.paginationInformation}"/>
            <table cellspacing="0" id="content"><tr><td><a href="javascript:toggle('createForm');"><fmt:message key="places.createnew" bundle="${bundle}"/></a></td></tr></table>
            <div id="createForm" style="display: none">
                <table cellspacing="0" id="content">
                    <tr>
                        <td>
                            <form method="POST" action="${pageContext.request.contextPath}/" onsubmit="return validate_form('<fmt:message key="error.validation.form" bundle="${bundle}"/>');">
                                <input type="hidden" name="command" value="create_place"/>
                                <input type="hidden" name="return" value="${requestScope.requestURI}"/>
                                <fmt:message key="places.name" bundle="${bundle}"/>:<br/>
                                <input type="text" name="name" class="inputform" value=""/><br/>
                                <fmt:message key="places.address" bundle="${bundle}"/>:<br/>
                                <input type="text" name="address" class="inputform" value=""/><br/>
                                <fmt:message key="places.type" bundle="${bundle}"/>:<br/>
                                <select name="type">
                                    <c:forEach items="${placetypeValues}" var="placetypeValue">
                                        <option value="${placetypeValue}"><fmt:message key="place.type.${placetypeValue}" bundle="${bundle}"/></option>
                                    </c:forEach>
                                </select><br/>
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


