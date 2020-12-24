package controller.command.Admin;

import controller.command.Command;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class BlockUserCommand implements Command {
    private UserService userService;


    public BlockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long userId = Long.parseLong(request.getParameter("blockedUser"));
        userService.blockUser(userId);
        return "/WEB-INF/view/ahome.jsp";
    }
}
