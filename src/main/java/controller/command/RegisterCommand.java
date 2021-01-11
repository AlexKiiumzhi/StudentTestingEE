package controller.command;


import controller.Utility.ParameterValidator;
import controller.Utility.UserValidator;
import model.entity.User;
import model.entity.enums.Role;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterCommand implements Command {
    private UserService userService;
    private ParameterValidator parameterValidator;

    public RegisterCommand(UserService userService, ParameterValidator parameterValidator) {
        this.userService = userService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String enFirstName = request.getParameter("enFirstName");
        String enLastName = request.getParameter("enLastName");
        String uaFirstName = request.getParameter("uaFirstName");
        String uaLastName = request.getParameter("uaLastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");

        if(!parameterValidator.validateNullNumber(age)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Age.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
        User user = new User();
        user.setRole(Role.USER);
        user.setEnFirstName(enFirstName);
        user.setEnLastName(enLastName);
        user.setUaFirstName(uaFirstName);
        user.setUaLastName(uaLastName);
        user.setEmail(email);
        user.setAge(Integer.parseInt(age));
        user.setPassword(password);
        user.setPhone(phone);

        List<String> errors = UserValidator.doValidate(user);

        if (!errors.isEmpty()) {

            request.setAttribute("errMsg", errors);

            return "/WEB-INF/view/validationerror.jsp";

        } else {
            userService.createUser(user);
            return "redirect:loginPage";
        }
    }
}