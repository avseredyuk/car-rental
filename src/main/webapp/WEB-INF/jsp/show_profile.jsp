<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.showprofile" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="leftmenu.jsp" %>
        <td width="100%">
            <table cellspacing="0" id="content">
                <tr>
                    <td>
                        <div id="errorStatus" style="display:<c:choose><c:when test="${empty errorStatus}">none</c:when><c:otherwise>block</c:otherwise></c:choose>"><a href="javascript:toggle('errorStatus');"><fmt:message key="${errorStatus}" bundle="${bundle}"/></a></div>
                        <form method="POST" action="${pageContext.request.contextPath}/" onsubmit="return validate_form('<fmt:message key="error.validation.form" bundle="${bundle}"/>');">
                            <input type="hidden" name="command" value="update_profile"/>
                            <fmt:message key="login.login" bundle="${bundle}"/>:<br/>
                            <input type="text" name="login" class="inputform" value="${fn:escapeXml(user.login)}" readonly/><br/>
                            <div id="hint"><fmt:message key="hint.profile.login" bundle="${bundle}"/></div><br/>
                            <fmt:message key="login.email" bundle="${bundle}"/>:<br/>
                            <input type="text" name="email" class="inputform" value="${fn:escapeXml(user.email)}"/><br/>
                            <fmt:message key="login.name" bundle="${bundle}"/>:<br/>
                            <input type="text" name="name" class="inputform" value="${fn:escapeXml(user.name)}"/><br/>
                            <fmt:message key="login.surname" bundle="${bundle}"/>:<br/>
                            <input type="text" name="surname" class="inputform" value="${fn:escapeXml(user.surname)}"/><br/>
                            <fmt:message key="users.registered" bundle="${bundle}"/><br/>
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.registered}"/><br/>
                            <fmt:message key="login.password" bundle="${bundle}"/>:<br/>
                            <%-- password field is empty --%>
                            <input type="text" name="password" value=""/><br/>
                            <div id="hint"><fmt:message key="hint.profile.password" bundle="${bundle}"/></div><br/>
                            <input type="submit" value="<fmt:message key="save" bundle="${bundle}"/>"/>
                        </form>
                    </td>
                </tr>
            </table>
            <br/>

            <c:choose>
            <c:when test="${fn:length(orders) == 0}">
                <table cellspacing="0" id="content">
                    <tr>
                        <td><fmt:message key="hint.no.data" bundle="${bundle}"/></td>
                    </tr>
                </table>
            </c:when>
            <c:otherwise>
            <table cellspacing="0" id="content">
                <tr><td id="caption" colspan="9"><b><fmt:message key="profile.lastorders" bundle="${bundle}"/></b></td></tr>
                <tr>
                    <th><fmt:message key="orders.locationfrom" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.locationto" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.automobile" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.damage" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.datefrom" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.dateto" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.datecreated" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.status" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.sum" bundle="${bundle}"/></th>
                </tr>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td id="rightcol">${fn:escapeXml(order.placeFrom.name)}</td>
                        <td id="rightcol">${fn:escapeXml(order.placeTo.name)}</td>
                        <td id="rightcol">${fn:escapeXml(order.automobile.manufacturer)} ${fn:escapeXml(order.automobile.model)} ${order.automobile.yearOfProduction}</td>
                        <td id="rightcol">
                            <c:choose>
                                <c:when test="${order.damage != null}">
                                    ${order.damage.damageSum}
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td id="rightcol"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.dateFrom}"/></td>
                        <td id="rightcol"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.dateTo}"/></td>
                        <td id="rightcol"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.created}"/></td>
                        <td id="rightcol"><fmt:message key="order.status.${order.status}" bundle="${bundle}"/></td>
                        <td id="rightcol">${order.sum}</td>
                    </tr>
                </c:forEach>
            </table>
            <ctg:pagination command="${requestScope.command}" returnURI="${requestScope.requestURI}" pageInfo="${requestScope.paginationInformation}"/>
            </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>
</body>
</html>