package controller.command.Admin;

import controller.Utility.ParameterValidator;
import controller.command.Command;
import controller.Utility.QuestionValidator;
import model.entity.Question;
import model.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditQuestionCommand implements Command {

    private QuestionService questionService;
    private ParameterValidator parameterValidator;

    public EditQuestionCommand(QuestionService questionService, ParameterValidator parameterValidator) {
        this.questionService = questionService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Long> answerIds1 = new ArrayList<>();

        String testId = request.getParameter("questionId");
        String enText = request.getParameter("enText1");
        String uaText = request.getParameter("uaText1");
        String[] answerIds = request.getParameterValues("answerIds");

        if(!parameterValidator.validateNullNumber(testId)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
        if (!parameterValidator.validateEmptyArray(answerIds)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("List.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
        Question question = new Question();
        question.setId(Long.parseLong(testId));
        question.setEnText(enText);
        question.setUaText(uaText);

        List<String> errors = QuestionValidator.doValidate(question);
        if (!errors.isEmpty()) {
            request.setAttribute("errMsg", errors);
            return "/WEB-INF/view/validationerror.jsp";

        } else {

            for (String id : answerIds) {
                answerIds1.add(Long.parseLong(id));
            }
            if (!parameterValidator.validateEmptyList(answerIds1)) {
                request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("List.validation_error"));
                return "/WEB-INF/view/valerror.jsp";
            }
            questionService.editQuestion(question, answerIds1);
            return "/WEB-INF/view/atestpage.jsp";
        }
    }
}