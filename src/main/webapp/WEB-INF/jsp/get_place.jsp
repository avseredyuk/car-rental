<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.place.get" bundle="${bundle}"/></title>
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
                            <input type="hidden" name="command" value="edit_place"/>
                            <input type="hidden" name="return" value="${requestScope.referer}"/>
                            <input type="hidden" name="place_id" value="${place.id}"/>
                            <fmt:message key="places.name" bundle="${bundle}"/>:<br/>
                            <input type="text" name="name" class="inputform" value="${fn:escapeXml(place.name)}"/><br/>
                            <fmt:message key="places.address" bundle="${bundle}"/>:<br/>
                            <input type="text" name="address" class="inputform" value="${fn:escapeXml(place.address)}"/><br/>
                            <fmt:message key="places.type" bundle="${bundle}"/>:<br/>
                            <select name="type">
                                <c:forEach items="${placetypeValues}" var="placetypeValue">
                                    <option value="${placetypeValue}" <c:if test="${place.type == placetypeValue}">selected</c:if>><fmt:message key="place.type.${placetypeValue}" bundle="${bundle}"/></option>
                                </c:forEach>
                            </select><br/>
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


