package controller.command.User;

import controller.command.Command;
import model.dto.TestsWithResultsDto;
import model.entity.Answer;
import model.entity.Test;
import model.entity.User;
import model.service.AnswerService;
import model.service.TestService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UserShowHomeCommand implements Command {

    private UserService userService;

    public UserShowHomeCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        Long userId = ((User)request.getSession().getAttribute("user")).getId();
        User user = userService.getUserInfo(userId);
        request.setAttribute("user", user);
        List<TestsWithResultsDto> testsWithResultsDtos = userService.getUserTests(userId);
        request.setAttribute("testsWithResultsDtos", testsWithResultsDtos);
        return "/WEB-INF/view/uhome.jsp";
    }
}