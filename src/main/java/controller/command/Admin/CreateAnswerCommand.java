package controller.command.Admin;

import controller.command.Command;
import model.entity.Answer;
import model.entity.Question;
import model.service.AnswerService;
import model.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

public class CreateAnswerCommand implements Command {

    private AnswerService answerService;

    public CreateAnswerCommand(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String enAnswer = request.getParameter("enAnswer");
        String uaAnswer = request.getParameter("uaAnswer");
        String correctnessState = request.getParameter("correctnessState");
        String questionId = request.getParameter("questionId1");


        Answer answer = new Answer();
        answer.setEnAnswer(enAnswer);
        answer.setUaAnswer(uaAnswer);
        answer.setCorrectnessState(Boolean.valueOf(correctnessState));

        answerService.createAnswer(answer, Long.parseLong(questionId));
        return "/WEB-INF/view/atestpage.jsp";
    }
}
