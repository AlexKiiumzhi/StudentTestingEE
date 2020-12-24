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

<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/createTest" method="post">
    <label for="enName"><fmt:message key="tests.en_name"/></label><br>
    <input type="text" id="enName" name="enName"><br><br>
    <label for="uaName"><fmt:message key="tests.ua_name"/></label><br>
    <input type="text" id="uaName" name="uaName"><br><br>
    <label for="difficulty1"><fmt:message key="tests.difficulty"/></label><br>
    <input type="text" id="difficulty1" name="difficulty1" lang="uk"><br><br>
    <label for="questionAmount1"><fmt:message key="tests.question_amount"/></label><br>
    <input type="text" id="questionAmount1" name="questionAmount1" lang="uk"><br><br>
    <label for="testDate1"><fmt:message key="tests.test_date"/></label><br>
    <input type="text" id="testDate1" name="testDate1"><br><br>
    <label for="subjectId"><fmt:message key="tests.subject_id"/></label><br>
    <input type="text" id="subjectId" name="subjectId"><br><br>
    <input type="submit" value=<fmt:message key="create.test"/>>
</form>

<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/editTest" method="post">
    <label for="testId"><fmt:message key="tests.id"/></label><br>
    <input type="text" id="testId" name="testId"><br><br>
    <label for="eName"><fmt:message key="tests.en_name"/></label><br>
    <input type="text" id="eName" name="eName"><br><br>
    <label for="uName"><fmt:message key="tests.ua_name"/></label><br>
    <input type="text" id="uName" name="uName"><br><br>
    <label for="Difficulty"><fmt:message key="tests.difficulty"/></label><br>
    <input type="text" id="Difficulty" name="Difficulty" lang="uk"><br><br>
    <label for="QuestionAmount"><fmt:message key="tests.question_amount"/></label><br>
    <input type="text" id="QuestionAmount" name="QuestionAmount" lang="uk"><br><br>
    <label for="TestDate"><fmt:message key="tests.test_date"/></label><br>
    <input type="text" id="TestDate" name="TestDate"><br><br>
    <input type="submit" value=<fmt:message key="update.test"/>>
</form>

<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/deleteTest" method="post">
    <label for="deletedTest"><fmt:message key="Test.delete"/></label><br>
    <input type="text" id="deletedTest" name="deletedTest"><br><br>
    <input type="submit" value=<fmt:message key="delete.test"/>>
</form>
</body>
</html>
