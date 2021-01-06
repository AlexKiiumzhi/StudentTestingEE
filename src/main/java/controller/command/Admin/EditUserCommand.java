package controller.command.Admin;

import controller.command.Command;
import model.entity.Question;
import model.entity.User;
import model.service.QuestionService;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class EditUserCommand implements Command {

    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
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
        String [] testIds = request.getParameterValues("testIds");

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

        for(String id: testIds){
            testIds1.add(Long.parseLong(id));
        }

        userService.editUser(user, testIds1);
        return "/WEB-INF/view/ahome.jsp";
    }
}
