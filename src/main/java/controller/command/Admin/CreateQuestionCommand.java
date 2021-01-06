package controller.command.Admin;

import controller.command.Command;
import model.entity.Question;
import model.entity.Test;
import model.service.QuestionService;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateQuestionCommand implements Command {

    private QuestionService questionService;

    public CreateQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String enText = request.getParameter("enText");
        String uaText = request.getParameter("uaText");
        String testId = request.getParameter("testId1");


        Question question = new Question();
        question.setEnText(enText);
        question.setUaText(uaText);

        questionService.createQuestion(question, Long.parseLong(testId));
        return "/WEB-INF/view/atestpage.jsp";
    }
}
