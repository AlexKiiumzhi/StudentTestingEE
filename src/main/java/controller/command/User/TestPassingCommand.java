package controller.command.User;

import controller.Utility.ParameterValidator;
import controller.command.Command;
import model.entity.Question;
import model.entity.User;
import model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class TestPassingCommand implements Command {

    private UserService userService;
    private ParameterValidator parameterValidator;


    public TestPassingCommand(UserService userService, ParameterValidator parameterValidator) {
        this.userService = userService;
        this.parameterValidator = parameterValidator;
    }

    @Override
    public String execute(HttpServletRequest request) {

        List<List<Long>> studentAnswers = new ArrayList<>();
        List<Long> answers1 = new ArrayList<>();
        Random rand = new Random();
        int low = 1;
        int high = 4;
        for (int i = 0; i < 2; i++)
        {
            long result = rand.nextInt(high - low);
            answers1.add(result);
        }
        List<Long> answers2 = new ArrayList<>();
        List<Long> answers3 = new ArrayList<>();
        for (int i = 0; i < 1; i++)
        {
            long result1 = rand.nextInt(high - low);
            answers2.add(result1);
            long result2 = rand.nextInt(high - low);
            answers3.add(result2);
        }
        studentAnswers.add(answers1);
        studentAnswers.add(answers2);
        studentAnswers.add(answers3);

        String testId = request.getParameter("testId1");

        if(!parameterValidator.validateNullNumber(testId)) {
            request.getSession().setAttribute("enErrorMessage", ResourceBundle.getBundle("outputs", Locale.getDefault()).getString("Id.validation_error"));
            return "/WEB-INF/view/valerror.jsp";
        }

        Long userId = ((User)request.getSession().getAttribute("user")).getId();
        userService.testPassing(userId, Long.parseLong(testId), studentAnswers);
        return "/WEB-INF/view/utestpassingpage.jsp";
    }
}
