package controller.command.Admin;

import controller.command.Command;
import model.entity.Answer;
import model.entity.Test;
import model.service.AnswerService;
import model.service.TestService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminShowHomeCommand implements Command {

    private TestService testService;

    public AdminShowHomeCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Test> tests = testService.findAll();
        request.setAttribute("tests", tests);

        return "/WEB-INF/view/ahome.jsp";
    }
}