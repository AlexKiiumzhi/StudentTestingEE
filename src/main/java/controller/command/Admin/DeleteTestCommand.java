package controller.command.Admin;

import controller.command.Command;
import model.entity.Test;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeleteTestCommand implements Command {

    private TestService testService;

    public DeleteTestCommand(TestService testService) {
        this.testService = testService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long testId = Long.parseLong(request.getParameter("deletedTest"));
        testService.deleteTest(testId);
        return "/WEB-INF/view/atestpage.jsp";
    }
}
