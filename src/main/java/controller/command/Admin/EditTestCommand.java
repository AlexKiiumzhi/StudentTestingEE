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

public class EditTestCommand implements Command {

    private TestService testService;
    private ParameterValidator parameterValidator;

    public EditTestCommand(TestService testService, ParameterValidator parameterValidator) {
        this.testService = testService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {


        String testId = request.getParameter("testId");
        String enName = request.getParameter("eName");
        String uaName = request.getParameter("uName");
        String difficulty = request.getParameter("Difficulty");
        String questionAmount = request.getParameter("QuestionAmount");
        String testDate = request.getParameter("TestDate");

        if(!parameterValidator.validateNullThreeNumbers(testId, difficulty, questionAmount)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.difficulty.Question_amount.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        Test test = new Test();
        test.setId(Long.parseLong(testId));
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
            testService.editTest(test);
            return "/WEB-INF/view/atestpage.jsp";
        }

    }
}
