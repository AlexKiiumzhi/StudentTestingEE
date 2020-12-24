package controller.command.Admin;

import controller.command.Command;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {
    private UserService userService;


    public UnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("unblockedUser"));
        userService.unblockUser(userId);
        return "/WEB-INF/view/ahome.jsp";
    }
}
