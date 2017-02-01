<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.main" bundle="${bundle}"/></title>
</head>
<body>
<table>
    <tr>
        <%@include file="leftmenu.jsp" %>
        <td width="100%">
            <table cellspacing="0" id="main">
                <tr id>
                    <td colspan="10" id="main">
                        <br/>
                        <fmt:message key="greeting.first" bundle="${bundle}"/>
                        <br/>
                        <br/>
                        <fmt:message key="greeting.second" bundle="${bundle}"/>
                        <br/>
                        <br/>
                    </td>
                </tr>
                <c:forEach items="${requestScope.automobiles}" var="automobile">
                    <tr><td></td></tr>
                    <tr>
                        <td id="main"><fmt:message key="automobiles.manufacturer" bundle="${bundle}"/></td>
                        <td id="main2"><b>${fn:escapeXml(automobile.manufacturer)}</b></td>

                        <td id="main"><fmt:message key="automobiles.year" bundle="${bundle}"/></td>
                        <td id="main2"><b>${automobile.yearOfProduction}</b></td>

                        <td id="main"><fmt:message key="automobiles.transmission" bundle="${bundle}"/></td>
                        <td id="main2"><b><fmt:message key="automobile.transmission.${automobile.transmission}" bundle="${bundle}"/></b></td>

                        <td id="main"><fmt:message key="automobiles.passengercapacity" bundle="${bundle}"/></td>
                        <td id="main2"><b>${automobile.passengerCapacity}</b></td>

                        <td id="main"><fmt:message key="automobiles.fuel" bundle="${bundle}"/></td>
                        <td id="main2"><b><fmt:message key="automobile.fuel.${automobile.fuel}" bundle="${bundle}"/></b></td>
                    </tr>
                    <tr>
                        <td id="main"><fmt:message key="automobiles.model" bundle="${bundle}"/></td>
                        <td id="main2"><b>${fn:escapeXml(automobile.model)}</b></td>

                        <td id="main"><fmt:message key="automobiles.category" bundle="${bundle}"/></td>
                        <td id="main2"><b><fmt:message key="automobile.category.${automobile.category}" bundle="${bundle}"/></b></td>

                        <td id="main"><fmt:message key="automobiles.doorscount" bundle="${bundle}"/></td>
                        <td id="main2"><b>${automobile.doorsCount}</b></td>

                        <td id="main"><fmt:message key="automobiles.cargocapacity" bundle="${bundle}"/></td>
                        <td id="main2"><b>${automobile.cargoCapacity}</b></td>

                        <td id="main"><fmt:message key="automobiles.priceperday" bundle="${bundle}"/></td>
                        <td id="main2"><b>${automobile.pricePerDay}</b></td>
                    </tr>
                </c:forEach>
                <tr><td></td></tr>
            </table>
            <ctg:pagination command="${requestScope.command}" returnURI="${requestScope.requestURI}" pageInfo="${requestScope.paginationInformation}"/>
        </td>
    </tr>
</table>

</body>
</html>