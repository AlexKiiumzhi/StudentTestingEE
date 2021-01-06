package controller.command.Admin;

import controller.command.Command;
import model.entity.Answer;
import model.entity.Subject;
import model.entity.Test;
import model.service.AnswerService;
import model.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowTestsPage implements Command {

    private AnswerService answerService;

    public ShowTestsPage(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Answer> answers = answerService.findAll();
        request.setAttribute("answers", answers);

        return "/WEB-INF/view/atestpage.jsp";
    }
}
