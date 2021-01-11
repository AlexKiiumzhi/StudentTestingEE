package controller.command.Admin;

import controller.Utility.ParameterValidator;
import controller.command.Command;
import model.entity.Test;
import model.service.TestService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class DeleteTestCommand implements Command {

    private TestService testService;
    private ParameterValidator parameterValidator;

    public DeleteTestCommand(TestService testService, ParameterValidator parameterValidator) {
        this.testService = testService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String testId = request.getParameter("deletedTest");

        if(!parameterValidator.validateNullNumber(testId)) {
                        request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
        testService.deleteTest(Long.parseLong(testId));
        return "/WEB-INF/view/atestpage.jsp";
    }
}
