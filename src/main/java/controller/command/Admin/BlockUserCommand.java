package controller.command.Admin;

import controller.Utility.ParameterValidator;
import controller.command.Command;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

public class BlockUserCommand implements Command {
    private UserService userService;
    private ParameterValidator parameterValidator;


    public BlockUserCommand(UserService userService, ParameterValidator parameterValidator) {
        this.userService = userService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        String id = request.getParameter("blockedUser");
        if(!parameterValidator.validateNullNumber(id)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        userService.blockUser(Long.parseLong(id));
        return "/WEB-INF/view/ahome.jsp";
    }
}
