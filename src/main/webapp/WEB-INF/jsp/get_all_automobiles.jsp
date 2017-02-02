<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.automobiles" bundle="${bundle}"/></title>
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
                    <th><fmt:message key="automobiles.manufacturer" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.model" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.year" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.location" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.category" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.fuel" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.transmission" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.passengercapacity" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.cargocapacity" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.doorscount" bundle="${bundle}"/></th>
                    <th><fmt:message key="automobiles.priceperday" bundle="${bundle}"/></th>
                    <th><fmt:message key="actions" bundle="${bundle}"/></th>
                </tr>
                <c:forEach items="${requestScope.automobiles}" var="automobile">
                    <tr>
                        <td id="rightcol">${fn:escapeXml(automobile.manufacturer)}</td>
                        <td id="rightcol">${fn:escapeXml(automobile.model)}</td>
                        <td id="rightcol">${automobile.yearOfProduction}</td>
                        <td id="rightcol">${fn:escapeXml(automobile.deliveryPlace.name)}</td>
                        <td id="rightcol"><fmt:message key="automobile.category.${automobile.category}" bundle="${bundle}"/></td>
                        <td id="rightcol"><fmt:message key="automobile.fuel.${automobile.fuel}" bundle="${bundle}"/></td>
                        <td id="rightcol"><fmt:message key="automobile.transmission.${automobile.transmission}" bundle="${bundle}"/></td>
                        <td id="rightcol">${automobile.passengerCapacity}</td>
                        <td id="rightcol">${automobile.cargoCapacity}</td>
                        <td id="rightcol">${automobile.doorsCount}</td>
                        <td id="rightcol">${automobile.pricePerDay}</td>
                        <td id="rightcol">
                            <a href="${pageContext.request.contextPath}/?command=get_automobile&automobile_id=${automobile.id}"><fmt:message key="action.edit" bundle="${bundle}"/></a>
                            <a href="${pageContext.request.contextPath}/?command=delete_automobile&automobile_id=${automobile.id}&return=${requestScope.requestURI}"><fmt:message key="action.delete" bundle="${bundle}"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <ctg:pagination command="${requestScope.command}" returnURI="${requestScope.requestURI}" pageInfo="${requestScope.paginationInformation}"/>
            <table cellspacing="0" id="content"><tr><td><a href="javascript:toggle('createForm');"><fmt:message key="automobiles.createnew" bundle="${bundle}"/></a></td></tr></table>
            <div id="createForm" style="display: none">
                <table cellspacing="0" id="content">
                    <tr>
                        <td>
                            <form method="POST" action="${pageContext.request.contextPath}/" onsubmit="return validate_form('<fmt:message key="error.validation.form" bundle="${bundle}"/>');">
                                <input type="hidden" name="command" value="create_automobile"/>
                                <input type="hidden" name="return" value="${requestScope.requestURI}"/>
                                <fmt:message key="automobiles.manufacturer" bundle="${bundle}"/>:<br/>
                                <input type="text" name="manufacturer" class="inputform" value=""/><br/>
                                <fmt:message key="automobiles.model" bundle="${bundle}"/>:<br/>
                                <input type="text" name="model" class="inputform" value=""/><br/>
                                <fmt:message key="automobiles.year" bundle="${bundle}"/>:<br/>
                                <input type="number" name="yearOfProduction" class="inputform" value=""/><br/>
                                <fmt:message key="automobiles.location" bundle="${bundle}"/>:<br/>
                                <select name="places">
                                    <c:forEach items="${places}" var="place">
                                        <option value="${place.id}">${fn:escapeXml(place.name)}</option>
                                    </c:forEach>
                                </select><br/>

                                <fmt:message key="automobiles.category" bundle="${bundle}"/>:<br/>
                                <select name="category">
                                    <c:forEach items="${categoryValues}" var="categoryValue">
                                        <option value="${categoryValue}"><fmt:message key="automobile.category.${categoryValue}" bundle="${bundle}"/></option>
                                    </c:forEach>
                                </select><br/>

                                <fmt:message key="automobiles.fuel" bundle="${bundle}"/>:<br/>
                                <select name="fuel">
                                    <c:forEach items="${fuelValues}" var="fuelValue">
                                        <option value="${fuelValue}"><fmt:message key="automobile.fuel.${fuelValue}" bundle="${bundle}"/></option>
                                    </c:forEach>
                                </select><br/>

                                <fmt:message key="automobiles.transmission" bundle="${bundle}"/>:<br/>
                                <select name="transmission">
                                    <c:forEach items="${transmissionValues}" var="transmissionValue">
                                        <option value="${transmissionValue}"><fmt:message key="automobile.transmission.${transmissionValue}" bundle="${bundle}"/></option>
                                    </c:forEach>
                                </select><br/>

                                <fmt:message key="automobiles.passengercapacity" bundle="${bundle}"/>:<br/>
                                <input type="number" name="passengerCapacity" class="inputform" value=""/><br/>
                                <fmt:message key="automobiles.cargocapacity" bundle="${bundle}"/>:<br/>
                                <input type="number" name="cargoCapacity" class="inputform" value=""/><br/>
                                <fmt:message key="automobiles.doorscount" bundle="${bundle}"/>:<br/>
                                <input type="number" name="doorsCount" class="inputform" value=""/><br/>
                                <fmt:message key="automobiles.priceperday" bundle="${bundle}"/>:<br/>
                                <input type="number" name="pricePerDay" class="inputform" value=""/><br/>
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


