package controller.command;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("user");
        String email = user.getEmail();
        CommandUtility.logoutUser(request, email);
        return "redirect:loginPage";
    }
}