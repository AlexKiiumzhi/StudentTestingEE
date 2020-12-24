package controller.command;

import model.entity.Subject;
import model.entity.Test;
import model.service.SubjectService;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*public class FindAllTestsByNameCommand implements Command {
    private TestService testService;


    public FindAllTestsByNameCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        List<Test> tests = testService.findAllByName();
        request.setAttribute("tests", tests);
        return "/WEB-INF/view/tests.jsp";
    }
}*/
