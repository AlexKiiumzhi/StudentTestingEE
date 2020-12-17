package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (request.getParameter("language").equals("en") || request.getParameter("language").equals("uk")) {
            Config.set(session, Config.FMT_LOCALE, new java.util.Locale(request.getParameter("language")));
        }
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}