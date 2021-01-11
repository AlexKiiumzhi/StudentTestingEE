<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<body>

<fmt:setBundle basename="outputs"/>
<c:set var="localeCode" value="${pageContext.response.locale}"/>
<fmt:message key="administrator.home_title" var="pageTitle"/>
<jsp:include page="aheader.jsp">
    <jsp:param name="title" value="${pageTitle}"/>
</jsp:include><br><br>

<div align="center">
    <h1><fmt:message key="create.test"/> </h1>
</div>

<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/createTest" method="post">
    <label for="enName"><fmt:message key="test.en_name"/></label><br>
    <input type="text" id="enName" name="enName"><br><br>
    <label for="uaName"><fmt:message key="test.ua_name"/></label><br>
    <input type="text" id="uaName" name="uaName"><br><br>
    <label for="difficulty1"><fmt:message key="test.difficulty"/></label><br>
    <input type="number" min="1" max="10" id="difficulty1" name="difficulty1" placeholder="from 1 to 10"><br><br>
    <label for="questionAmount1"><fmt:message key="test.question_amount"/></label><br>
    <input type="number" id="questionAmount1" name="questionAmount1" placeholder="question amount number"><br><br>
    <label for="testDate1"><fmt:message key="test.test_date"/></label><br>
    <input type="text" id="testDate1" name="testDate1" placeholder="YYYY-MM-DD HH:MM"><br><br>
    <label for="subjectId"><fmt:message key="test.subject_id"/></label><br>
    <input type="number" min="1" id="subjectId" name="subjectId" placeholder="id number"><br><br>
    <input type="submit" value=<fmt:message key="create.test"/>>
</form>
<div align="center">
    <h1><fmt:message key="update.test"/> </h1>
</div>
<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/editTest" method="post">
    <label for="testId"><fmt:message key="test.id"/></label><br>
    <input type="number" id="testId" name="testId" placeholder="id number"><br><br>
    <label for="eName"><fmt:message key="test.en_name"/></label><br>
    <input type="text" id="eName" name="eName"><br><br>
    <label for="uName"><fmt:message key="test.ua_name"/></label><br>
    <input type="text" id="uName" name="uName"><br><br>
    <label for="Difficulty"><fmt:message key="test.difficulty"/></label><br>
    <input type="number" min="1" max="10" id="Difficulty" name="Difficulty" placeholder="from 1 to 10"><br><br>
    <label for="QuestionAmount"><fmt:message key="test.question_amount"/></label><br>
    <input type="number" id="QuestionAmount" name="QuestionAmount" placeholder="question amount number"><br><br>
    <label for="TestDate"><fmt:message key="test.test_date"/></label><br>
    <input type="text" id="TestDate" name="TestDate" placeholder="YYYY-MM-DD HH:MM"><br><br>
    <input type="submit" value=<fmt:message key="update.test"/>>
</form>
<div align="center">
    <h1><fmt:message key="delete.test"/> </h1>
</div>
<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/deleteTest" method="post">
    <label for="deletedTest"><fmt:message key="Test.delete"/></label><br>
    <input type="number" min="1" id="deletedTest" name="deletedTest" placeholder="id number"><br><br>
    <input type="submit" value=<fmt:message key="delete.test"/>>
</form>
<div align="center">
    <h1><fmt:message key="create.question"/> </h1>
</div>
<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/createQuestion" method="post">
    <label for="enText"><fmt:message key="question.text_en"/></label><br>
    <input type="text" id="enText" name="enText"><br><br>
    <label for="uaText"><fmt:message key="question.text_ua"/></label><br>
    <input type="text" id="uaText" name="uaText"><br><br>
    <label for="testId1"><fmt:message key="question.test_id"/></label><br>
    <input type="number" min="1"  id="testId1" name="testId1" placeholder="id number"><br><br>
    <input type="submit" value=<fmt:message key="create.question"/>>
</form>
<div align="center">
    <h1><fmt:message key="update.question"/> </h1>
</div>
<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/editQuestion" method="post">
    <label for="questionId"><fmt:message key="question.id"/></label><br>
    <input type="number" min="1" id="questionId" name="questionId" placeholder="id number"><br><br>
    <label for="enText1"><fmt:message key="test.en_name"/></label><br>
    <input type="text" id="enText1" name="enText1"><br><br>
    <label for="uaText1"><fmt:message key="test.ua_name"/></label><br>
    <input type="text" id="uaText1" name="uaText1"><br><br>
    <select name="answerIds" multiple="multiple">
        <c:forEach items="${answers}" var="answer">
            <c:choose>
                <c:when test="${localeCode == 'uk'}">
                    <option value="${answer.id}" ><c:out value="${answer.uaAnswer}"/></option>
                </c:when>
                <c:otherwise>
                    <option value="${answer.id}" ><c:out value="${answer.enAnswer}"/></option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
    <input type="submit" value=<fmt:message key="update.question"/>>
</form>
<div align="center">
    <h1><fmt:message key="create.answer"/> </h1>
</div>
<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/createAnswer" method="post">
    <label for="enAnswer"><fmt:message key="answer.answer_en"/></label><br>
    <input type="enAnswer" id="enAnswer" name="enAnswer"><br><br>
    <label for="uaAnswer"><fmt:message key="answer.answer_ua"/></label><br>
    <input type="text" id="uaAnswer" name="uaAnswer"><br><br>
    <label for="correctnessState"><fmt:message key="answer.correctness_state"/></label><br>
    <input type="text" id="correctnessState" name="correctnessState" placeholder="true or false"><br><br>
    <label for="questionId1"><fmt:message key="answer.question_id"/></label><br>
    <input type="number" min="1"  id="questionId1" name="questionId1" placeholder="id number"><br><br>
    <input type="submit" value=<fmt:message key="create.answer"/>>
</form>
<div align="center">
    <h1><fmt:message key="update.answer"/> </h1>
</div>
<form class="w3-center" action="${pageContext.request.contextPath}/app/admin/testPage/editAnswer" method="post">
    <label for="answerId"><fmt:message key="answer.id"/></label><br>
    <input type="number" min="1" id="answerId" name="answerId" placeholder="id number"><br><br>
    <label for="enAnswer1"><fmt:message key="answer.answer_en"/></label><br>
    <input type="text" id="enAnswer1" name="enAnswer1"><br><br>
    <label for="uaAnswer1"><fmt:message key="answer.answer_ua"/></label><br>
    <input type="text" id="uaAnswer1" name="uaAnswer1"><br><br>
    <label for="correctnessState1"><fmt:message key="answer.correctness_state"/></label><br>
    <input type="text" id="correctnessState1" name="correctnessState1" placeholder="true or false"><br><br>
    <input type="submit" value=<fmt:message key="update.answer"/>>
</form>
</body>
</html>
