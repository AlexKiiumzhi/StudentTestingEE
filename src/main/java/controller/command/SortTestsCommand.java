package controller.command;

import model.entity.Subject;
import model.entity.Test;
import model.service.SubjectService;
import model.service.TestService;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SortTestsCommand implements Command {
    private TestService testService;
    private SubjectService subjectService;


    public SortTestsCommand(TestService testService, SubjectService subjectService) {
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
        return "/WEB-INF/view/tests.jsp";
    }
}
