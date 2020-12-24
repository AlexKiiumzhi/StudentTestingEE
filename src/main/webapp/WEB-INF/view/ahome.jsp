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
        <form class="w3-center" action="${pageContext.request.contextPath}/app/admin/blockUser" method="post">
        <label for="blockedUser"><fmt:message key="User.block"/></label><br>
        <input type="text" id="blockedUser" name="blockedUser"><br><br>
            <input type="submit" value=<fmt:message key="Block.button"/>>
        </form>
        <form class="w3-center" action="${pageContext.request.contextPath}/app/admin/unblockUser" method="post">
            <label for="unblockedUser"><fmt:message key="User.unblock"/></label><br>
            <input type="text" id="unblockedUser" name="unblockedUser"><br><br>
            <input type="submit" value=<fmt:message key="Unblock.button"/>>
        </form>
      <%--  <c:if test="${empty passed}">
            <form action="${pageContext.request.contextPath}/app/admin/stopAdmission" method="post">
                <input type="submit" value="<fmt:message key="administrator.stop_admission"/>"/>
            </form>
        </c:if>
        <c:if test="${not empty passed}">
            <fmt:message key="message.stop_admission"/>
        </c:if><br>--%>
    </body>
</html>