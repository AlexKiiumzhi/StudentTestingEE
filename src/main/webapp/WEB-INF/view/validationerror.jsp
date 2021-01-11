<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<fmt:setBundle basename="outputs"/>
<body>
    <c:forEach items="${errMsg}" var="err" >
        <c:out value="${err}"/>
        <br>
    </c:forEach>
</body>
</html>