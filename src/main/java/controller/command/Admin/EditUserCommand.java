package controller.command.Admin;

import controller.Utility.ParameterValidator;
import controller.Utility.UserValidator;
import controller.command.Command;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class EditUserCommand implements Command {

    private UserService userService;
    private ParameterValidator parameterValidator;

    public EditUserCommand(UserService userService, ParameterValidator parameterValidator) {
        this.userService = userService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<Long> testIds1 = new ArrayList<>();

        String userId = request.getParameter("userId");
        String enFirstName = request.getParameter("enFirstName");
        String uaFirstName = request.getParameter("uaFirstName");
        String enLastName = request.getParameter("enLastName");
        String uaLastName = request.getParameter("uaLastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String[] testIds = request.getParameterValues("testIds");

        if(!parameterValidator.validateNullTwoNumbers(userId, age)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.Age.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
        if (!parameterValidator.validateEmptyArray(testIds)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("List.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }
        User user = new User();
        user.setId(Long.parseLong(userId));
        user.setEnFirstName(enFirstName);
        user.setUaFirstName(uaFirstName);
        user.setEnLastName(enLastName);
        user.setUaLastName(uaLastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setAge(Integer.parseInt(age));
        user.setPhone(phone);

        List<String> errors = UserValidator.doValidate(user);

        if (!errors.isEmpty()) {
            request.setAttribute("errMsg", errors);
            return "/WEB-INF/view/validationerror.jsp";
        } else {

            for (String id : testIds) {
                testIds1.add(Long.parseLong(id));
            }
            if (!parameterValidator.validateEmptyList(testIds1)) {
                request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("List.validation_error"));
                return "/WEB-INF/view/valerror.jsp";
            }
            userService.editUser(user, testIds1);
            return "/WEB-INF/view/ahome.jsp";
        }
    }
}