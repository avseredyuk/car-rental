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
                <c:choose>
                    <c:when test="${empty sessionScope.date_from and empty sessionScope.date_to and empty sessionScope.place_from and empty sessionScope.place_to}">
                        <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${requestScope.date_now}" var="date_from"/>
                        <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${requestScope.date_now}" var="date_to"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${sessionScope.date_from}" var="date_from"/>
                        <fmt:formatDate pattern="yyyy-MM-dd'T'HH:mm" value="${sessionScope.date_to}" var="date_to"/>
                        <c:set var="place_from" value="${sessionScope.place_from}"/>
                        <c:set var="place_to" value="${sessionScope.place_to}"/>
                    </c:otherwise>
                </c:choose>

                <form method="POST" action="${pageContext.request.contextPath}/?command=make_order&step=step2" style="display: inline;">
                <tr><td id="main" colspan="4"></td></tr>
                <tr>
                    <td id="main">
                        <fmt:message key="makeorder.choose.date.from" bundle="${bundle}"/>
                    </td>
                    <td id="main">
                        <input type="datetime-local" name="date_from" id="date_from" value="${date_from}">
                    </td>
                    <td id="main">
                        <fmt:message key="makeorder.choose.place.from" bundle="${bundle}"/>
                    </td>
                    <td id="main">
                        <select name="place_from">
                            <c:forEach items="${places}" var="place">
                                <option value="${place.id}" <c:if test="${place_from.id == place.id}">selected</c:if>>${fn:escapeXml(place.name)}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td id="main">
                        <fmt:message key="makeorder.choose.date.to" bundle="${bundle}"/>
                    </td>
                    <td id="main">
                        <input type="datetime-local" name="date_to" id="date_to" value="${date_to}">
                    </td>
                    <td id="main">
                        <fmt:message key="makeorder.choose.place.to" bundle="${bundle}"/>
                    </td>
                    <td id="main">
                        <select name="place_to">
                            <c:forEach items="${places}" var="place">
                                <option value="${place.id}" <c:if test="${place_to.id == place.id}">selected</c:if>>${fn:escapeXml(place.name)}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr><td id="main" colspan="4"></td></tr>

                <tr><td></td></tr>
                <tr><td id="main" colspan="4">
                    <input type="submit" value="<fmt:message key="order.new.next" bundle="${bundle}"/>"/>
                </form>
                </td></tr>
            </table>
        </td>
    </tr>
</table>

</body>
</html>