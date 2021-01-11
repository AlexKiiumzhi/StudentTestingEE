package controller.command.User;

import controller.Utility.ParameterValidator;
import controller.command.Command;
import model.entity.Question;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestSelectionCommand implements Command {

    private UserService userService;
    private ParameterValidator parameterValidator;


    public TestSelectionCommand(UserService userService, ParameterValidator parameterValidator) {
        this.userService = userService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String testId = request.getParameter("testId");

        if(!parameterValidator.validateNullNumber(testId)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        Long userId = ((User)request.getSession().getAttribute("user")).getId();
        List<Question> questions = userService.testSelection(userId, Long.parseLong(testId));
        request.setAttribute("questions", questions);
        return "/WEB-INF/view/utestpassingpage.jsp";
    }
}
