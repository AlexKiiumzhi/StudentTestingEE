<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <body>
        <fmt:setBundle basename="outputs"/>
        <fmt:message key="administrator.home_title" var="pageTitle"/>
        <jsp:include page="aheader.jsp">
            <jsp:param name="title" value="${pageTitle}"/>
        </jsp:include><br><br>
        <c:if test="${empty passed}">
            <form action="${pageContext.request.contextPath}/app/admin/stopAdmission" method="post">
                <input type="submit" value="<fmt:message key="administrator.stop_admission"/>"/>
            </form>
        </c:if>
        <c:if test="${not empty passed}">
            <fmt:message key="message.stop_admission"/>
        </c:if><br>
    </body>
</html>