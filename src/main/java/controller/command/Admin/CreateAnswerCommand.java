package controller.command.Admin;

import controller.Utility.AnswerValidator;
import controller.Utility.ParameterValidator;
import controller.command.Command;
import model.entity.Answer;
import model.entity.Question;
import model.service.AnswerService;
import model.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CreateAnswerCommand implements Command {

    private AnswerService answerService;
    private ParameterValidator parameterValidator;

    public CreateAnswerCommand(AnswerService answerService, ParameterValidator parameterValidator) {
        this.answerService = answerService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String enAnswer = request.getParameter("enAnswer");
        String uaAnswer = request.getParameter("uaAnswer");
        String correctnessState = request.getParameter("correctnessState");
        String questionId = request.getParameter("questionId1");

        if (!parameterValidator.validateNullNumber(questionId)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        if (!parameterValidator.validateEmpty(correctnessState)) {
                       request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Boolean.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        Answer answer = new Answer();
        answer.setEnAnswer(enAnswer);
        answer.setUaAnswer(uaAnswer);
        answer.setCorrectnessState(Boolean.valueOf(correctnessState));

        List<String> errors = AnswerValidator.doValidate(answer);
        if (!errors.isEmpty()) {
            request.setAttribute("errMsg", errors);
            return "/WEB-INF/view/validationerror.jsp";

        } else {
            answerService.createAnswer(answer, Long.parseLong(questionId));
            return "/WEB-INF/view/atestpage.jsp";
        }
    }
}