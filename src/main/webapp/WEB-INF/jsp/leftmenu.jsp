<td>
    <table cellspacing="0" id="menu">
        <tr>
            <td id="leftcol">
                <a href="${pageContext.request.contextPath}/?command=show_index">
                    <fmt:message key="menu.main" bundle="${bundle}"/>
                </a>
            </td>
        </tr>
        <c:choose>
            <c:when test="${sessionScope.role == null}">
                <%-- GUEST / NO ROLE --%>
                <tr>
                    <td id="leftcol">
                        <a href="${pageContext.request.contextPath}/?command=make_order&step=step1">
                            <fmt:message key="menu.makeorder" bundle="${bundle}"/>
                        </a>
                    </td>
                </tr>
                <tr><td></td></tr>
                <tr>
                    <td id="leftcol">
                        <a href="${pageContext.request.contextPath}/?command=show_login">
                            <fmt:message key="menu.login" bundle="${bundle}"/>
                        </a>
                    </td>
                </tr>
                <tr>
                    <td id="leftcol">
                        <a href="${pageContext.request.contextPath}/?command=show_register">
                            <fmt:message key="menu.register" bundle="${bundle}"/>
                        </a>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <%-- LOGGED IN / SOME ROLE --%>
                <c:choose>
                    <c:when test="${sessionScope.role == 'CLIENT'}">
                        <%-- LOGGED IN / CLIENT --%>
                        <tr>
                            <td id="leftcol">
                                <a href="${pageContext.request.contextPath}/?command=make_order&step=step1">
                                    <fmt:message key="menu.makeorder" bundle="${bundle}"/>
                                </a>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <%-- LOGGED IN / ADMIN --%>
                        <tr>
                            <td id="leftcol">
                                <a href="${pageContext.request.contextPath}/?command=get_all_automobiles">
                                    <fmt:message key="menu.admin.automobiles" bundle="${bundle}"/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td id="leftcol">
                                <a href="${pageContext.request.contextPath}/?command=get_all_users">
                                    <fmt:message key="menu.admin.users" bundle="${bundle}"/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td id="leftcol">
                                <a href="${pageContext.request.contextPath}/?command=get_all_places">
                                    <fmt:message key="menu.admin.places" bundle="${bundle}"/>
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td id="leftcol">
                                <a href="${pageContext.request.contextPath}/?command=get_all_orders">
                                    <fmt:message key="menu.admin.orders" bundle="${bundle}"/>
                                </a>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <%-- LOGGED IN COMMON LINKS --%>
                <tr><td></td></tr>
                <tr>
                    <td id="leftcol">
                        <fmt:message key="menu.loggedas" bundle="${bundle}"/>
                        <a href="${pageContext.request.contextPath}/?command=show_profile">
                            ${fn:escapeXml(sessionScope.login)}
                        </a>
                    </td>
                </tr>
                <tr>
                    <td id="leftcol">
                        <form method="POST" action="${pageContext.request.contextPath}/" style="display: inline;">
                            <a href="javascript:" onclick="parentNode.submit();"><fmt:message key="menu.logout" bundle="${bundle}"/></a>
                            <input type="hidden" name="command" value="logout"/>
                        </form>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
        <%-- for logged and guests both --%>
        <tr><td></td></tr>
        <tr>
            <td id="leftcol">
                <form method="POST" action="${pageContext.request.contextPath}/" style="display: inline;">
                    <input type="hidden" name="command" value="change_locale"/>
                    <input type="hidden" name="return" value="${requestScope.requestURI}"/>
                    <select name="locale" onchange="this.form.submit()">
                        <option value="en_US" <c:if test="${sessionScope.locale == 'en_US'}">selected</c:if>>English</option>
                        <option value="ru_RU" <c:if test="${sessionScope.locale == 'ru_RU'}">selected</c:if>>Russian</option>
                    </select>
                </form>
            </td>
        </tr>
    </table>
</td>