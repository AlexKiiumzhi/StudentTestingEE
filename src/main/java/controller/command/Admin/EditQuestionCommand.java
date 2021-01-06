package controller.command.Admin;

import controller.command.Command;
import model.entity.Question;
import model.service.QuestionService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class EditQuestionCommand implements Command {

    private QuestionService questionService;

    public EditQuestionCommand(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Long> answerIds1 = new ArrayList<>();

        String testId = request.getParameter("questionId");
        String enText = request.getParameter("enText1");
        String uaText = request.getParameter("uaText1");
        String [] answerIds = request.getParameterValues("answerIds");

        Question question = new Question();
        question.setId(Long.parseLong(testId));
        question.setEnText(enText);
        question.setUaText(uaText);

        for(String id: answerIds){
            answerIds1.add(Long.parseLong(id));
        }

        questionService.editQuestion(question, answerIds1);
        return "/WEB-INF/view/atestpage.jsp";
    }
}
