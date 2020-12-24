package controller.command;

import model.entity.Subject;
import model.entity.Test;
import model.service.SubjectService;
import model.service.TestService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAllTestsCommand implements Command {
    private TestService testService;
    private SubjectService subjectService;

    public FindAllTestsCommand(TestService testService, SubjectService subjectService) {
        this.testService = testService;
        this.subjectService = subjectService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int recordPerPage = 3;
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int startIndex = (pageNumber * recordPerPage) - recordPerPage;
        int totalNumberOfRecords = testService.numberOfRows();
        int numberOfPages = totalNumberOfRecords / recordPerPage;
        if (totalNumberOfRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        List<Test> tests = testService.findAllPagination(startIndex, recordPerPage);
        System.out.println(tests);
        List<Subject> subjects = subjectService.findAll();
        request.setAttribute("tests", tests);
        request.setAttribute("subjects", subjects);
        request.setAttribute("numberOfPages", numberOfPages);
        return "/WEB-INF/view/tests.jsp";
    }
}