package controller;

import controller.command.*;
import controller.command.Admin.*;
import model.service.SubjectService;
import model.service.TestService;
import model.service.UserService;

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

        commands.put("admin/home", new AdminShowHomeCommand());
        commands.put("admin/blockUser", new BlockUserCommand(new UserService()));
        commands.put("admin/unblockUser", new UnblockUserCommand(new UserService()));
        commands.put("admin/testPage", new ShowTestsPage());
        commands.put("admin/testPage/createTest", new CreateTestCommand(new TestService()));
        commands.put("admin/testPage/editTest", new EditTestCommand(new TestService()));
        commands.put("admin/testPage/deleteTest", new DeleteTestCommand(new TestService()));

        commands.put("home", new ShowHomeCommand());
        commands.put("searchBySubject", new SearchTestBySubjectCommand(new TestService(), new SubjectService()));
        commands.put("tests", new FindAllTestsCommand(new TestService(), new SubjectService()));
        commands.put("registrationPage", new ShowRegistrationCommand());
        commands.put("registration", new RegisterCommand(new UserService()));
        commands.put("login", new LoginCommand(new UserService()));
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
        String path = request.getRequestURI();
        path = path.replaceAll(".*/app/", "");
        Command command = commands.getOrDefault(path, (r) -> "/WEB-INF/view/login.jsp");
        String page = command.execute(request);
        if (page.contains("redirect")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
