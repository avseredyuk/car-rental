<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.makeorder" bundle="${bundle}"/></title>
</head>
<body onload="selectFirstItemRow()">
<table>
    <tr>
        <%@include file="leftmenu.jsp" %>
        <td width="100%">
                <c:choose>
                    <c:when test="${fn:length(automobiles) == 0}">
                    <table cellspacing="0" id="main">
                        <tr>
                            <td id="main">
                                <br/>
                                <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step1" style="display: inline;">
                                    <fmt:message key="orders.noavailable" bundle="${bundle}"/><br/>
                                <br>
                            </td>
                        </tr>
                        <tr><td></td></tr>
                        <tr>
                            <td id="main">
                                <input type="submit" value="<fmt:message key="order.new.back" bundle="${bundle}"/>"/>
                                </form>
                            </td>
                        </tr>
                    </table>
                    </c:when>
                    <c:otherwise>
                    <table cellspacing="0" id="main">
                        <tbody>
                        <tr><td id="main"><fmt:message key="makeorder.choose.date.from" bundle="${bundle}"/></td><td id="main"><b><fmt:formatDate type="both" value="${sessionScope.date_from}"/></b></td></tr>
                        <tr><td id="main"><fmt:message key="makeorder.choose.date.to" bundle="${bundle}"/></td><td id="main"><b><fmt:formatDate type="both" value="${sessionScope.date_to}"/></b></td></tr>
                        <tr><td id="main"><fmt:message key="makeorder.choose.place.from" bundle="${bundle}"/></td><td id="main"><b>${fn:escapeXml(sessionScope.place_from.name)}</b></td></tr>
                        <tr><td id="main"><fmt:message key="makeorder.choose.place.to" bundle="${bundle}"/></td><td id="main"><b>${fn:escapeXml(sessionScope.place_to.name)}</b></td></tr>

                        <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step3">

                        <c:choose>
                        <c:when test="${not empty sessionScope.automobile}">
                            <c:set var="automobile_id" value="${sessionScope.automobile.id}"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="automobile_id" value=""/>
                        </c:otherwise>
                        </c:choose>

                        <tr><td></td></tr>
                        <tr><td id="main"><fmt:message key="orders.automobiles" bundle="${bundle}"/></td>
                            <td id="main">
                                <select id=automobileselect" name="automobiles" onchange="showItemBySelect(this)">
                                <c:forEach items="${automobiles}" var="automobile">
                                    <option value="${automobile.id}" <c:if test="${automobile.id == automobile_id}">selected</c:if>>${fn:escapeXml(automobile.manufacturer)} ${fn:escapeXml(automobile.model)}</option>
                                </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr><td></td></tr>
                        </tbody>
                        <c:forEach items="${automobiles}" var="automobile">
                        <tbody class="item" id="${automobile.id}" style="display: ${(((automobile.id == automobiles[0].id) && (empty automobile_id)) || (automobile.id == automobile_id)) ? "" : "none" }">
                            <tr><td id="main"><fmt:message key="automobiles.manufacturer" bundle="${bundle}"/></td><td id="main"><b>${fn:escapeXml(automobile.manufacturer)}</b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.model" bundle="${bundle}"/></td><td id="main"><b>${fn:escapeXml(automobile.model)}</b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.year" bundle="${bundle}"/></td><td id="main"><b>${automobile.yearOfProduction}</b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.category" bundle="${bundle}"/></td><td id="main"><b><fmt:message key="automobile.category.${automobile.category}" bundle="${bundle}"/></b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.fuel" bundle="${bundle}"/></td><td id="main"><b><fmt:message key="automobile.fuel.${automobile.fuel}" bundle="${bundle}"/></b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.transmission" bundle="${bundle}"/></td><td id="main"><b><fmt:message key="automobile.transmission.${automobile.transmission}" bundle="${bundle}"/></b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.passengercapacity" bundle="${bundle}"/></td><td id="main"><b>${automobile.passengerCapacity}</b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.cargocapacity" bundle="${bundle}"/></td><td id="main"><b>${automobile.cargoCapacity}</b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.doorscount" bundle="${bundle}"/></td><td id="main"><b>${automobile.doorsCount}</b></td></tr>
                            <tr><td id="main"><fmt:message key="automobiles.priceperday" bundle="${bundle}"/></td><td id="main"><b>${automobile.pricePerDay}</b></td></tr>
                        </tbody>
                        </c:forEach>
                        <tbody>
                        <tr><td></td></tr>
                        <tr>
                            <td colspan="2" id="main">
                                <input type="submit" value="<fmt:message key="order.new.next" bundle="${bundle}"/>"/>
                                </form>
                            </td>
                        </tr>
                        <tr><td></td></tr>
                        <tr>
                            <td colspan="2" id="main">
                                <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step1" style="display: inline;">
                                <input type="submit" value="<fmt:message key="order.new.back" bundle="${bundle}"/>"/>
                                </form>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </c:otherwise>

                </c:choose>
        </td>
    </tr>
</table>

</body>
</html>