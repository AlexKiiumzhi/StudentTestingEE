package controller;

import controller.Utility.ParameterValidator;
import controller.command.*;
import controller.command.Admin.*;
import controller.command.User.*;
import model.entity.enums.Role;
import model.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Servlet extends HttpServlet {
    private HashMap<String, Command> commands = new HashMap<>();

    @Override
    public void init() {
        getServletContext().setAttribute("loggedUsers", new HashSet<String>());

        commands.put("admin/home", new AdminShowHomeCommand(new TestService()));
        commands.put("admin/blockUser", new BlockUserCommand(new UserService(), new ParameterValidator()));
        commands.put("admin/unblockUser", new UnblockUserCommand(new UserService(), new ParameterValidator()));
        commands.put("admin/testPage", new ShowTestsPage(new AnswerService()));
        commands.put("admin/testPage/createTest", new CreateTestCommand(new TestService(), new ParameterValidator()));
        commands.put("admin/testPage/editTest", new EditTestCommand(new TestService(), new ParameterValidator()));
        commands.put("admin/testPage/deleteTest", new DeleteTestCommand(new TestService(), new ParameterValidator()));
        commands.put("admin/testPage/createQuestion", new CreateQuestionCommand(new QuestionService(), new ParameterValidator()));
        commands.put("admin/testPage/editQuestion", new EditQuestionCommand(new QuestionService(), new ParameterValidator()));
        commands.put("admin/testPage/createAnswer", new CreateAnswerCommand(new AnswerService(), new ParameterValidator()));
        commands.put("admin/testPage/editAnswer", new EditAnswerCommand(new AnswerService(), new ParameterValidator()));
        commands.put("admin/home/editUser", new EditUserCommand(new UserService(), new ParameterValidator()));
        commands.put("admin/searchBySubject", new AdminSearchTestBySubjectCommand(new TestService(), new SubjectService()));
        commands.put("admin/sortTests", new AdminSortTestsCommand(new TestService(), new SubjectService()));
        commands.put("admin/tests", new AdminFindAllTestsCommand(new TestService(), new SubjectService()));

        commands.put("user/home", new UserShowHomeCommand(new UserService()));
        commands.put("user/testPassingPage", new ShowTestPassingPage());
        commands.put("user/testPassingPage/testSelecting", new TestSelectionCommand(new UserService(), new ParameterValidator()));
        commands.put("user/testPassingPage/testPassing", new TestPassingCommand(new UserService(), new ParameterValidator()));
        commands.put("user/searchBySubject", new UserSearchTestBySubjectCommand(new TestService(), new SubjectService()));
        commands.put("user/sortTests", new UserSortTestsCommand(new TestService(), new SubjectService()));
        commands.put("user/tests", new UserFindAllTestsCommand(new TestService(), new SubjectService()));

        commands.put("home", new ShowHomeCommand());
        commands.put("searchBySubject", new SearchTestBySubjectCommand(new TestService(), new SubjectService()));
        commands.put("sortTests", new SortTestsCommand(new TestService(), new SubjectService()));
        commands.put("tests", new FindAllTestsCommand(new TestService(), new SubjectService()));
        commands.put("registrationPage", new ShowRegistrationCommand());
        commands.put("registration", new RegisterCommand(new UserService(), new ParameterValidator()));
        commands.put("loginPage", new ShowLoginCommand());
        commands.put("login", new LoginCommand(new UserService(), new ParameterValidator()));
        commands.put("logout", new LogoutCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userRoleAsString = (String) (request).getSession().getAttribute("role");
        Role userRole = null;
        if(userRoleAsString == null) {
            userRole = Role.GUEST;
        } else if (userRoleAsString.equals("USER")){
            userRole = Role.USER;
        }else if (userRoleAsString.equals("ADMIN")){
            userRole = Role.ADMIN;
        }
        String path = request.getRequestURI();
        Command command;
        if (userRole.equals(Role.GUEST) && (path.contains("user") || path.contains("admin"))) {
            command = commands.get("home");
        } else if (path.contains("user") && !userRole.equals(Role.USER)) {
            command = commands.get("admin/home");
        } else if (path.contains("admin") && !userRole.equals(Role.ADMIN)) {
            command = commands.get("user/home");
        } else {
            path = path.replaceAll(".*/app/", "");
            command = commands.getOrDefault(path, (r) -> "/WEB-INF/view/error.jsp");
        }
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
