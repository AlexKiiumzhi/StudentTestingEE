package controller.command.User;

import controller.command.Command;
import model.entity.Question;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TestSelectionCommand implements Command {

    private UserService userService;

    public TestSelectionCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String testId = request.getParameter("testId");
        Long userId = ((User)request.getSession().getAttribute("user")).getId();
        List<Question> questions = userService.testSelection(userId, Long.parseLong(testId));
        request.setAttribute("questions", questions);
        return "/WEB-INF/view/utestpassingpage.jsp";
    }
}
