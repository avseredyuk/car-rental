<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.adminmain" bundle="${bundle}"/></title>
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
                    <td id="caption" colspan="11">
                        <b><fmt:message key="admin.lastorders" bundle="${bundle}"/></b>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/?command=get_all_orders"><fmt:message key="admin.showall" bundle="${bundle}"/></a>
                    </td>
                </tr>
                <tr>
                    <th><fmt:message key="orders.locationfrom" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.locationto" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.automobile" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.user" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.damage" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.datefrom" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.dateto" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.datecreated" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.status" bundle="${bundle}"/></th>
                    <th><fmt:message key="orders.sum" bundle="${bundle}"/></th>
                    <th><fmt:message key="actions" bundle="${bundle}"/></th>
                </tr>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td id="rightcol"><a href="${pageContext.request.contextPath}/?command=get_place&place_id=${order.placeFrom.id}">${fn:escapeXml(order.placeFrom.name)}</a></td>
                        <td id="rightcol"><a href="${pageContext.request.contextPath}/?command=get_place&place_id=${order.placeTo.id}">${fn:escapeXml(order.placeTo.name)}</a></td>
                        <td id="rightcol"><a href="${pageContext.request.contextPath}/?command=get_automobile&automobile_id=${order.automobile.id}">${fn:escapeXml(order.automobile.manufacturer)} ${fn:escapeXml(order.automobile.model)} ${order.automobile.yearOfProduction}</a></td>
                        <td id="rightcol"><a href="${pageContext.request.contextPath}/?command=get_user&user_id=${order.user.id}">${fn:escapeXml(order.user.name)} ${fn:escapeXml(order.user.surname)}</a></td>
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
                        <td id="rightcol">
                            <a href="${pageContext.request.contextPath}/?command=get_order&order_id=${order.id}"><fmt:message key="action.edit" bundle="${bundle}"/></a>
                            <a href="${pageContext.request.contextPath}/?command=delete_order&order_id=${order.id}&return=/?command=show_index"><fmt:message key="action.delete" bundle="${bundle}"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <table cellspacing="0" id="content">
                <tr>
                    <td id="caption" colspan="7">
                        <b><fmt:message key="admin.lastusers" bundle="${bundle}"/></b>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/?command=get_all_users"><fmt:message key="admin.showall" bundle="${bundle}"/></a>
                    </td>
                </tr>
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
                            <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${user.registered}"/>
                        </td>
                        <td id="rightcol">
                            <a href="${pageContext.request.contextPath}/?command=get_user&user_id=${user.id}"><fmt:message key="action.edit" bundle="${bundle}"/></a>
                            <a href="${pageContext.request.contextPath}/?command=delete_user&user_id=${user.id}&return=/?command=show_index"><fmt:message key="action.delete" bundle="${bundle}"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>

</body>
</html>