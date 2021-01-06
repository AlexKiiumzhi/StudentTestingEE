package controller.command.User;

import controller.command.Command;
import model.entity.Subject;
import model.entity.Test;
import model.service.SubjectService;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserSortTestsCommand implements Command {
    private TestService testService;
    private SubjectService subjectService;


    public UserSortTestsCommand(TestService testService, SubjectService subjectService) {
        this.testService = testService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String sortingParameter = request.getParameter("sortingParameter");
        List<Test> tests = testService.sortTests(sortingParameter);
        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("tests", tests);
        request.setAttribute("subjects", subjects);
        return "/WEB-INF/view/utests.jsp";
    }
}
