package controller.command;


import model.entity.User;
import model.entity.enums.Role;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Locale;
import java.util.ResourceBundle;

public class RegisterCommand implements Command {
    private UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Locale uklocale = new Locale("uk", "UA");
        ResourceBundle ukResourceBundle = ResourceBundle.getBundle("outputs", uklocale);
        ResourceBundle enResourceBundle = ResourceBundle.getBundle("outputs", Locale.getDefault());
        /*@NotNull(message = "question id must not be null")
        @Pattern(regexp="[A-Z0-9a-z]{3,20}",message="length must be from 3 to 20")
        @NotEmpty(message = "ukrainian answer name must not be empty")*/
        String enFirstName = request.getParameter("enFirstName");
        String enLastName = request.getParameter("enLastName");
        String uaFirstName = request.getParameter("uaFirstName");
        String uaLastName = request.getParameter("uaLastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setRole(Role.USER);
        user.setEnFirstName(enFirstName);
        user.setEnLastName(enLastName);
        user.setUaFirstName(uaFirstName);
        user.setUaLastName(uaLastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        if(!userService.validateNullRegistration(user, age)) {
            request.getSession().setAttribute("ukErrorMessage", ukResourceBundle.getString("registration.null_error"));
            request.getSession().setAttribute("enErrorMessage", enResourceBundle.getString("registration.null_error"));
            return "redirect:registrationPage";
        }
        if(!userService.validateRegistration(user, age)) {
            request.getSession().setAttribute("ukErrorMessage", ukResourceBundle.getString("registration.validation_error"));
            request.getSession().setAttribute("enErrorMessage", enResourceBundle.getString("registration.validation_error"));
            return "redirect:registrationPage";
        }
        user.setAge(Integer.parseInt(age));
        userService.createUser(user);
        return "redirect:loginPage";
    }
}