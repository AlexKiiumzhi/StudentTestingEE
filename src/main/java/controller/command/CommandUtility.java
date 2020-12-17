package controller.command;

import model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    static boolean checkUserIsLogged(HttpServletRequest request, String email, User user) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(email::equals)) {
            return true;
        }
        loggedUsers.add(email);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return false;
    }

    static void logoutUser(HttpServletRequest request, String email) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        loggedUsers.remove(email);
        request.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
    }
}