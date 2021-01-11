package controller.command;

import controller.Utility.ParameterValidator;
import model.entity.User;
import model.entity.enums.Role;
import model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import java.util.*;

public class LoginCommand implements Command {
    private Map<Role, String> pages = new HashMap<>();
    private UserService userService;
    private ParameterValidator parameterValidator;

    public LoginCommand(UserService userService, ParameterValidator parameterValidator) {
        this.userService = userService;
        this.parameterValidator = parameterValidator;
        pages.put(Role.ADMIN, "redirect:admin/home");
        pages.put(Role.USER, "redirect:user/home");
    }

    @Override
    public String execute(HttpServletRequest request) {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(parameterValidator.validateLogin(email, password)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("login.null_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
            User user = userService.findByEmail(email);
            if (password.equals(user.getPassword())) {
                if (CommandUtility.checkUserIsLogged(request, user.getEmail(), user)) {
                    return "/WEB-INF/view/error.jsp";
                }
                return pages.getOrDefault(user.getRole(), "/WEB-INF/view/login.jsp");
            }
            return "/WEB-INF/view/loginerror.jsp";
        }
    }
