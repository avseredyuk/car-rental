<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.order.get" bundle="${bundle}"/></title>
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
                            <input type="hidden" name="command" value="edit_order"/>
                            <input type="hidden" name="return" value="${requestScope.referer}"/>
                            <input type="hidden" name="order_id" value="${order.id}"/>

                            <fmt:message key="orders.locationfrom" bundle="${bundle}"/>:<br/>
                            <select name="places_from">
                                <c:forEach items="${places}" var="place">
                                    <option value="${place.id}" <c:if test="${order.placeFrom.id == place.id}">selected</c:if>>${fn:escapeXml(place.name)}</option>
                                </c:forEach>
                            </select><br/>

                            <fmt:message key="orders.locationto" bundle="${bundle}"/>:<br/>
                            <select name="places_to">
                                <c:forEach items="${places}" var="place">
                                    <option value="${place.id}" <c:if test="${order.placeTo.id == place.id}">selected</c:if>>${fn:escapeXml(place.name)}</option>
                                </c:forEach>
                            </select><br/>

                            <fmt:message key="orders.automobile" bundle="${bundle}"/>:<br/>
                            <select name="automobiles">
                                <c:forEach items="${automobiles}" var="automobile">
                                    <option value="${automobile.id}" <c:if test="${order.automobile.id == automobile.id}">selected</c:if>>${fn:escapeXml(automobile.manufacturer)} ${fn:escapeXml(automobile.model)}</option>
                                </c:forEach>
                            </select><br/>

                            <fmt:message key="orders.user" bundle="${bundle}"/>:<br/>
                            <select name="users">
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.id}" <c:if test="${order.user.id == user.id}">selected</c:if>>${fn:escapeXml(user.login)}</option>
                                </c:forEach>
                            </select><br/>

                            <input type="hidden" name="damage_id" value="${order.damage.id}"/>
                            <fmt:message key="orders.damage.sum" bundle="${bundle}"/>:<br/>
                            <input type="number" name="damage_sum" value="${order.damage.damageSum}"/><br/>
                            <fmt:message key="orders.damage.description" bundle="${bundle}"/>:<br/>
                            <input type="text" name="damage_description" value="${fn:escapeXml(order.damage.description)}"/><br/>
                            <fmt:message key="orders.damage.paid" bundle="${bundle}"/>:<br/>
                            <input type="checkbox" name="damage_paid" <c:if test="${order.damage.paid}">checked="checked"</c:if> /><br/>

                            <fmt:message key="orders.datefrom" bundle="${bundle}"/>:<br/>
                            <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${order.dateFrom}" var="dateFrom"/>
                            <input type="datetime-local" name="date_from" value="${dateFrom}"><br/>

                            <fmt:message key="orders.dateto" bundle="${bundle}"/>:<br/>
                            <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${order.dateTo}" var="dateTo"/>
                            <input type="datetime-local" name="date_to" value="${dateTo}"><br/>

                            <fmt:message key="orders.datecreated" bundle="${bundle}"/>:<br/>
                            <fmt:formatDate pattern="DD.MM.YYYY HH:mm:ss" value="${order.created}"/><br/>

                            <fmt:message key="orders.status" bundle="${bundle}"/>:<br/>
                            <select name="status">
                                <c:forEach items="${statusValues}" var="statusValue">
                                    <option value="${statusValue}" <c:if test="${order.status == statusValue}">selected</c:if>><fmt:message key="order.status.${statusValue}" bundle="${bundle}"/></option>
                                </c:forEach>
                            </select><br/>

                            <fmt:message key="orders.sum" bundle="${bundle}"/>:<br/>
                            <input type="number" name="order_sum" class="inputform" value="${order.sum}"/><br/>

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


