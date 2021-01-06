package controller.command.Admin;

import controller.command.Command;
import model.entity.Answer;
import model.entity.Question;
import model.service.AnswerService;

import javax.servlet.http.HttpServletRequest;

public class EditAnswerCommand implements Command {

    private AnswerService answerService;

    public EditAnswerCommand(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String answerId = request.getParameter("answerId");
        String enAnswer = request.getParameter("enAnswer1");
        String uaAnswer = request.getParameter("uaAnswer1");
        String correctnessState = request.getParameter("correctnessState1");

        Answer answer = new Answer();
        answer.setId(Long.parseLong(answerId));
        answer.setEnAnswer(enAnswer);
        answer.setUaAnswer(uaAnswer);
        answer.setCorrectnessState(Boolean.valueOf(correctnessState));

        answerService.editAnswer(answer);
        return "/WEB-INF/view/atestpage.jsp";
    }
}
