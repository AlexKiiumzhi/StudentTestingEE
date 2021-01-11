package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.dto.TestsWithResultsDto;
import model.entity.Question;
import model.entity.User;

import java.util.List;
import java.util.regex.Pattern;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createUser(User user) {
        UserDao dao = daoFactory.createUserDao();
        dao.createUser(user);
    }

    public User getUserInfo(Long userId) {
        UserDao dao = daoFactory.createUserDao();
        return dao.getUserRegistrationInfo(userId);
    }

    public List<TestsWithResultsDto> getUserTests(Long userId) {
        UserDao dao = daoFactory.createUserDao();
        return dao.getUserTests(userId);
    }

    public User findByEmail(String email) {
        UserDao dao = daoFactory.createUserDao();
        return dao.findByEmail(email);

    }

    public void blockUser(Long userId){
        UserDao dao = daoFactory.createUserDao();
        dao.blockUser(userId);
    }

    public void unblockUser(Long userId){
        UserDao dao = daoFactory.createUserDao();
        dao.unblockUser(userId);
    }

    public void editUser(User user, List<Long> testIds) {
        UserDao dao = daoFactory.createUserDao();
        dao.editUser(user, testIds);
    }

    public List<Question> testSelection(Long userId, Long testId) {
        UserDao dao = daoFactory.createUserDao();
        return dao.testSelection(userId, testId);
    }

    public void testPassing(Long userId, Long testId, List<List<Long>> studentAnswers) {
        UserDao dao = daoFactory.createUserDao();
        dao.testPassing(userId, testId, studentAnswers);
    }

}
