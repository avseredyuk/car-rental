<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.makeorder" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="leftmenu.jsp" %>
        <td width="100%">
            <table cellspacing="0" id="main">
                <tr><td id="main"><fmt:message key="makeorder.choose.date.from" bundle="${bundle}"/></td><td id="main2"><b><fmt:formatDate type="both" value="${sessionScope.date_from}"/></b></td></tr>
                <tr><td id="main"><fmt:message key="makeorder.choose.date.to" bundle="${bundle}"/></td><td id="main2"><b><fmt:formatDate type="both" value="${sessionScope.date_to}"/></b></td></tr>
                <tr><td id="main"><fmt:message key="makeorder.choose.place.from" bundle="${bundle}"/></td><td id="main2"><b>${fn:escapeXml(sessionScope.place_from.name)}</b></td></tr>
                <tr><td id="main"><fmt:message key="makeorder.choose.place.to" bundle="${bundle}"/></td><td id="main2"><b>${fn:escapeXml(sessionScope.place_to.name)}</b></td></tr>
                <tr><td></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.manufacturer" bundle="${bundle}"/></td><td id="main2"><b>${fn:escapeXml(automobile.manufacturer)}</b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.model" bundle="${bundle}"/></td><td id="main2"><b>${fn:escapeXml(automobile.model)}</b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.year" bundle="${bundle}"/></td><td id="main2"><b>${automobile.yearOfProduction}</b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.category" bundle="${bundle}"/></td><td id="main2"><b><fmt:message key="automobile.category.${automobile.category}" bundle="${bundle}"/></b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.fuel" bundle="${bundle}"/></td><td id="main2"><b><fmt:message key="automobile.fuel.${automobile.fuel}" bundle="${bundle}"/></b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.transmission" bundle="${bundle}"/></td><td id="main2"><b><fmt:message key="automobile.transmission.${automobile.transmission}" bundle="${bundle}"/></b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.passengercapacity" bundle="${bundle}"/></td><td id="main2"><b>${automobile.passengerCapacity}</b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.cargocapacity" bundle="${bundle}"/></td><td id="main2"><b>${automobile.cargoCapacity}</b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.doorscount" bundle="${bundle}"/></td><td id="main2"><b>${automobile.doorsCount}</b></td></tr>
                <tr><td id="main"><fmt:message key="automobiles.priceperday" bundle="${bundle}"/></td><td id="main2"><b>${automobile.pricePerDay}</b></td></tr>
                <tr><td></td></tr>
                <tr>
                    <td id="main">
                        <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step4" style="display: inline;">
                            <fmt:message key="makeorder.order.total" bundle="${bundle}"/>
                    </td>
                    <td id="main2">
                        <b>${sessionScope.order_sum}</b>
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td id="main" colspan="2">
                            <input type="submit" value="<fmt:message key="order.new.orderbutton" bundle="${bundle}"/>"/>
                        </form>
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td id="main" colspan="2">
                        <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step2" style="display: inline;">
                            <input type="submit" value="<fmt:message key="order.new.back" bundle="${bundle}"/>"/>
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>