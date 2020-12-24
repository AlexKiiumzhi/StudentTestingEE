package controller.command.Admin;

import controller.command.Command;
import model.entity.Test;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditTestCommand implements Command {

    private TestService testService;

    public EditTestCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String testId = request.getParameter("testId");
        String enName = request.getParameter("eName");
        String uaName = request.getParameter("uName");
        String difficulty = request.getParameter("Difficulty");
        String questionAmount = request.getParameter("QuestionAmount");
        String testDate = request.getParameter("TestDate");



    Test test = new Test();
    test.setId(Long.parseLong(testId));
    test.setEnName(enName);
    test.setUaName(uaName);
    test.setDifficulty(Long.parseLong(difficulty));
    test.setQuestionAmount(Integer.parseInt(questionAmount));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    test.setTestDate(LocalDateTime.parse(testDate, formatter));


        testService.editTest(test);
        return "/WEB-INF/view/atestpage.jsp";
    }
}
