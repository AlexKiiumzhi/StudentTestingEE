package controller.command;

import javax.servlet.http.HttpServletRequest;

public class ShowLoginCommand implements  Command{
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/login.jsp";
    }
}
