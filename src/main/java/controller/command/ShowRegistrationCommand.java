package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ShowRegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/registration.jsp";
    }
}