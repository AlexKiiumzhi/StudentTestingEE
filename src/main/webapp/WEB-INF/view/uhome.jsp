<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <c:set var="localeCode" value="${pageContext.response.locale}"/>
        <fmt:message key="user.home_title" var="pageTitle"/>
        <jsp:include page="uheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br><br>
       <%-- <form action="${pageContext.request.contextPath}/app/client/showMarks" method="post">
            <input type="submit" value="<fmt:message key="client.marks"/>"/>
        </form>--%>
        <%--<c:if test="${not empty results}">
            <table>
                <thead>
                    <tr>
                        <th><fmt:message key="client.mark_subject"/></th>
                        <th><fmt:message key="client.mark_mark"/></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${results}" var="result">
                        <c:if test="${result.email == user.email}">
                            <c:forEach items="${result.exams}" var="exam">
                                <tr>
                                    <c:if test="${exam.mark != 0}">
                                        <c:choose>
                                            <c:when test="${localeCode == 'uk'}">
                                                <th><c:out value="${exam.uaName}"/></th>
                                            </c:when>
                                            <c:otherwise>
                                                <th><c:out value="${exam.enName}"/></th>
                                            </c:otherwise>
                                        </c:choose>
                                        <th><c:out value="${exam.mark}"/></th>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>--%>
        <%--</c:if>
        <c:if test="${not empty passed}">
            <c:forEach items="${passed}" var="pass">
                    <c:if test="${user.email == pass}">
                        <fmt:message key="message.pass"/>
                    </c:if>
            </c:forEach>
            <c:forEach items="${notPassed}" var="notPass">
                    <c:if test="${user.email == notPass}">
                        <fmt:message key="message.fail"/>
                    </c:if>
            </c:forEach>
        </c:if>--%>
    </body>
</html>