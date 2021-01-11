package controller.command.Admin;

import controller.Utility.ParameterValidator;
import controller.Utility.TestValidator;
import controller.command.Command;
import model.entity.Test;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateTestCommand implements Command {

    private TestService testService;
    private ParameterValidator parameterValidator;

    public CreateTestCommand(TestService testService, ParameterValidator parameterValidator) {

        this.testService = testService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {


        String enName = request.getParameter("enName");
        String uaName = request.getParameter("uaName");
        String difficulty = request.getParameter("difficulty1");
        String questionAmount = request.getParameter("questionAmount1");
        String testDate = request.getParameter("testDate1");
        String subjectId = request.getParameter("subjectId");

        if(!parameterValidator.validateNullThreeNumbers(subjectId, difficulty, questionAmount)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.difficulty.Question_amount.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        Test test = new Test();
        test.setEnName(enName);
        test.setUaName(uaName);
        test.setDifficulty(Long.parseLong(difficulty));
        test.setQuestionAmount(Integer.parseInt(questionAmount));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        test.setTestDate(LocalDateTime.parse(testDate, formatter));

        List<String> errors = TestValidator.doValidate(test);

        if (!errors.isEmpty()) {
            request.setAttribute("errMsg", errors);
            return "/WEB-INF/view/validationerror.jsp";
        } else {
            testService.createTest(test, Long.parseLong(subjectId));
            return "/WEB-INF/view/atestpage.jsp";
        }
    }
}