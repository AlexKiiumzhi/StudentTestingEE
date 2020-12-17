package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ShowHomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/home.jsp";
    }
}