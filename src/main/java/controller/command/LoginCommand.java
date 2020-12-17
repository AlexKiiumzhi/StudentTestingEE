package controller.command;

import model.entity.User;
import model.entity.enums.Role;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private Map<Role, String> pages = new HashMap<>();
    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
        pages.put(Role.ADMIN, "redirect:admin/home");
        pages.put(Role.USER, "redirect:client/home");
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale uklocale = new Locale("uk", "UA");
        ResourceBundle ukResourceBundle = ResourceBundle.getBundle("outputs", uklocale);
        ResourceBundle enResourceBundle = ResourceBundle.getBundle("outputs", Locale.getDefault());
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(!userService.validateNullLogin(email, password)) {
            request.getSession().setAttribute("ukErrorMessage", ukResourceBundle.getString("login.null_error"));
            request.getSession().setAttribute("enErrorMessage", enResourceBundle.getString("login.null_error"));
            return "/WEB-INF/view/login.jsp";
        }
        User user = userService.findByEmail(email);
        if (password.equals(user.getPassword())) {
            if (CommandUtility.checkUserIsLogged(request, user.getEmail(), user)) {
                return "/WEB-INF/view/error.jsp";
            }
            return pages.getOrDefault(user.getRole(), "/WEB-INF/view/login.jsp");
        }
        return "/WEB-INF/view/login.jsp";
    }
}