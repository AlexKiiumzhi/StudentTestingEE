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

        <table class="w3-table w3-bordered w3-striped">
            <thead>
            <tr>
                <th><fmt:message key="user.id"/></th>
                <th><fmt:message key="user.first_name_en"/></th>
                <th><fmt:message key="user.last_name_en"/></th>
                <th><fmt:message key="user.first_name_ua"/></th>
                <th><fmt:message key="user.last_name_ua"/></th>
                <th><fmt:message key="user.email"/></th>
                <th><fmt:message key="user.password"/></th>
                <th><fmt:message key="user.age"/></th>
                <th><fmt:message key="user.phone"/></th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <c:choose>
                        <c:when test="${localeCode == 'uk'}">
                            <th><c:out value="${user.id}"/></th>
                            <th><c:out value="${user.enFirstName}"/></th>
                            <th><c:out value="${user.uaFirstName}"/></th>
                            <th><c:out value="${user.enLastName}"/></th>
                            <th><c:out value="${user.uaLastName}"/></th>
                            <th><c:out value="${user.email}"/></th>
                            <th><c:out value="${user.password}"/></th>
                            <th><c:out value="${user.age}"/></th>
                            <th><c:out value="${user.phone}"/></th>

                        </c:when>
                        <c:otherwise>
                            <th><c:out value="${user.id}"/></th>
                            <th><c:out value="${user.enFirstName}"/></th>
                            <th><c:out value="${user.uaFirstName}"/></th>
                            <th><c:out value="${user.enLastName}"/></th>
                            <th><c:out value="${user.uaLastName}"/></th>
                            <th><c:out value="${user.email}"/></th>
                            <th><c:out value="${user.password}"/></th>
                            <th><c:out value="${user.age}"/></th>
                            <th><c:out value="${user.phone}"/></th>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </tbody>
        </table>

        <div align="center">
            <h1><fmt:message key="create.test"/> </h1>
        </div>

        <table class="w3-table w3-bordered w3-striped">
            <thead>
            <tr>
                <th><fmt:message key="testsWithResultsDto.testId"/></th>
                <th><fmt:message key="testsWithResultsDto.testEnName"/></th>
                <th><fmt:message key="testsWithResultsDto.testUaName"/></th>
                <th><fmt:message key="testsWithResultsDto.subject"/></th>
                <th><fmt:message key="testsWithResultsDto.mark"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${testsWithResultsDtos}" var="testsWithResultsDto">
            <tr>
                <c:choose>
                    <c:when test="${localeCode == 'uk'}">
                        <th><c:out value="${testsWithResultsDto.testId}"/></th>
                        <th><c:out value="${testsWithResultsDto.testEnName}"/></th>
                        <th><c:out value="${testsWithResultsDto.testUaName}"/></th>
                        <th><c:out value="${testsWithResultsDto.subject}"/></th>
                        <th><c:out value="${testsWithResultsDto.mark}"/></th>
                    </c:when>
                    <c:otherwise>
                        <th><c:out value="${testsWithResultsDto.testId}"/></th>
                        <th><c:out value="${testsWithResultsDto.testEnName}"/></th>
                        <th><c:out value="${testsWithResultsDto.testUaName}"/></th>
                        <th><c:out value="${testsWithResultsDto.subject}"/></th>
                        <th><c:out value="${testsWithResultsDto.mark}"/></th>
                    </c:otherwise>
                </c:choose>
            </tr>
            </c:forEach>
            </tbody>
        </table>


    </body>
</html>