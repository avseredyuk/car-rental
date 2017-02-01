<%@include file="/WEB-INF/jsp/includes/header.jspf" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/styles.css"/>
    <script src="js/functions.js"></script>
    <title><fmt:message key="caption.automobile.get" bundle="${bundle}"/></title>
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
                            <input type="hidden" name="command" value="edit_automobile"/>
                            <input type="hidden" name="return" value="${requestScope.referer}"/>
                            <input type="hidden" name="automobile_id" value="${automobile.id}"/>
                            <fmt:message key="automobiles.manufacturer" bundle="${bundle}"/>:<br/>
                            <input type="text" name="manufacturer" class="inputform" value="${fn:escapeXml(automobile.manufacturer)}"/><br/>
                            <fmt:message key="automobiles.model" bundle="${bundle}"/>:<br/>
                            <input type="text" name="model" class="inputform" value="${fn:escapeXml(automobile.model)}"/><br/>
                            <fmt:message key="automobiles.year" bundle="${bundle}"/>:<br/>
                            <input type="number" name="yearOfProduction" class="inputform" value="${automobile.yearOfProduction}"/><br/>
                            <fmt:message key="automobiles.location" bundle="${bundle}"/>:<br/>
                            <select name="places">
                                <c:forEach items="${places}" var="place">
                                    <option value="${place.id}" <c:if test="${automobile.deliveryPlace.id == place.id}">selected</c:if>>${fn:escapeXml(place.name)}</option>
                                </c:forEach>
                            </select><br/>
                            <fmt:message key="automobiles.category" bundle="${bundle}"/>:<br/>
                            <select name="category">
                                <c:forEach items="${categoryValues}" var="categoryValue">
                                    <option value="${categoryValue}" <c:if test="${automobile.category == categoryValue}">selected</c:if>><fmt:message key="automobile.category.${categoryValue}" bundle="${bundle}"/></option>
                                </c:forEach>
                            </select><br/>
                            <fmt:message key="automobiles.fuel" bundle="${bundle}"/>:<br/>
                            <select name="fuel">
                                <c:forEach items="${fuelValues}" var="fuelValue">
                                    <option value="${fuelValue}" <c:if test="${automobile.fuel == fuelValue}">selected</c:if>><fmt:message key="automobile.fuel.${fuelValue}" bundle="${bundle}"/></option>
                                </c:forEach>
                            </select><br/>
                            <fmt:message key="automobiles.transmission" bundle="${bundle}"/>:<br/>
                            <select name="transmission">
                                <c:forEach items="${transmissionValues}" var="transmissionValue">
                                    <option value="${transmissionValue}" <c:if test="${automobile.transmission == transmissionValue}">selected</c:if>><fmt:message key="automobile.transmission.${transmissionValue}" bundle="${bundle}"/></option>
                                </c:forEach>
                            </select><br/>
                            <fmt:message key="automobiles.passengercapacity" bundle="${bundle}"/>:<br/>
                            <input type="number" name="passengerCapacity" class="inputform" value="${automobile.passengerCapacity}"/><br/>
                            <fmt:message key="automobiles.cargocapacity" bundle="${bundle}"/>:<br/>
                            <input type="number" name="cargoCapacity" class="inputform" value="${automobile.cargoCapacity}"/><br/>
                            <fmt:message key="automobiles.doorscount" bundle="${bundle}"/>:<br/>
                            <input type="number" name="doorsCount" class="inputform" value="${automobile.doorsCount}"/><br/>
                            <fmt:message key="automobiles.priceperday" bundle="${bundle}"/>:<br/>
                            <input type="number" name="pricePerDay" class="inputform" value="${automobile.pricePerDay}"/><br/>
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


