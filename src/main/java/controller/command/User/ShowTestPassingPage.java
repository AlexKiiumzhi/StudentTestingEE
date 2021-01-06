package controller.command.User;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;


public class ShowTestPassingPage implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return "/WEB-INF/view/utestpassingpage.jsp";
    }
}
