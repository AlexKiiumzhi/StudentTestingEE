package controller.command.Admin;

import controller.Utility.ParameterValidator;
import controller.command.Command;
import controller.Utility.AnswerValidator;
import model.entity.Answer;
import model.service.AnswerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditAnswerCommand implements Command {

    private AnswerService answerService;
    private ParameterValidator parameterValidator;

    public EditAnswerCommand(AnswerService answerService, ParameterValidator parameterValidator) {
        this.answerService = answerService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String answerId = request.getParameter("answerId");
        String enAnswer = request.getParameter("enAnswer1");
        String uaAnswer = request.getParameter("uaAnswer1");
        String correctnessState = request.getParameter("correctnessState1");

        if(!parameterValidator.validateNullNumber(answerId)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
        if(!parameterValidator.validateEmpty(correctnessState)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Boolean.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        Answer answer = new Answer();
        answer.setId(Long.parseLong(answerId));
        answer.setEnAnswer(enAnswer);
        answer.setUaAnswer(uaAnswer);
        answer.setCorrectnessState(Boolean.valueOf(correctnessState));

        List<String> errors = AnswerValidator.doValidate(answer);
        if (!errors.isEmpty()) {
            request.setAttribute("errMsg", errors);
            return "/WEB-INF/view/validationerror.jsp";

        } else {
            answerService.editAnswer(answer);
            return "/WEB-INF/view/atestpage.jsp";
        }
    }
}