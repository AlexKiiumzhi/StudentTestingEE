package controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminShowHomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/ahome.jsp";
    }
}