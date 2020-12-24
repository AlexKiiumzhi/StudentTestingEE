package controller.command.Admin;

import controller.command.Command;
import model.entity.Subject;
import model.entity.Test;
import model.entity.User;
import model.entity.enums.Role;
import model.service.TestService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateTestCommand implements Command {

    private TestService testService;

    public CreateTestCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String enName = request.getParameter("enName");
        String uaName = request.getParameter("uaName");
        String difficulty = request.getParameter("difficulty1");
        String questionAmount = request.getParameter("questionAmount1");
        String testDate = request.getParameter("testDate1");
        String subjectId = request.getParameter("subjectId");


    Test test = new Test();
    test.setEnName(enName);
    test.setUaName(uaName);
    test.setDifficulty(Long.parseLong(difficulty));
    test.setQuestionAmount(Integer.parseInt(questionAmount));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    test.setTestDate(LocalDateTime.parse(testDate, formatter));


        testService.createTest(test, Long.parseLong(subjectId));
        return "/WEB-INF/view/atestpage.jsp";
    }
}