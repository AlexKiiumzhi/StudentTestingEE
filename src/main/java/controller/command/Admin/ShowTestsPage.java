package controller.command.Admin;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;

public class ShowTestsPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/atestpage.jsp";
    }
}
