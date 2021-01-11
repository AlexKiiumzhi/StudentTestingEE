package model.dao;

import model.dto.TestsWithResultsDto;
import model.entity.Question;
import model.entity.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);
    User getUserRegistrationInfo(Long userId);
    List<TestsWithResultsDto> getUserTests(Long userId);
    User findByEmail(String email);
    void blockUser(Long userId);
    void unblockUser(Long userId);
    void editUser(User user, List<Long> testIds);
    List<Question> testSelection(Long userID, Long testId);
    void testPassing(Long userID, Long testId, List<List<Long>> studentAnswers);
}
