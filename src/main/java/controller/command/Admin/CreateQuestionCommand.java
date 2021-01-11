package controller.command.Admin;

import controller.Utility.ParameterValidator;
import controller.Utility.QuestionValidator;
import controller.command.Command;
import model.entity.Question;
import model.entity.Test;
import model.service.QuestionService;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateQuestionCommand implements Command {

    private QuestionService questionService;
    private ParameterValidator parameterValidator;

    public CreateQuestionCommand(QuestionService questionService, ParameterValidator parameterValidator) {
        this.questionService = questionService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String enText = request.getParameter("enText");
        String uaText = request.getParameter("uaText");
        String testId = request.getParameter("testId1");

        if(!parameterValidator.validateNullNumber(testId)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        Question question = new Question();
        question.setEnText(enText);
        question.setUaText(uaText);

        List<String> errors = QuestionValidator.doValidate(question);
        if (!errors.isEmpty()) {
            request.setAttribute("errMsg", errors);
            return "/WEB-INF/view/validationerror.jsp";

        } else {
            questionService.createQuestion(question, Long.parseLong(testId));
            return "/WEB-INF/view/atestpage.jsp";
        }
    }
}