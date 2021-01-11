package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.*;
import model.dto.TestsWithResultsDto;
import model.entity.*;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class JDBCUserFactory implements UserDao {

    static final Logger logger = Logger.getLogger(JDBCUserFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCUserFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCUserFactory: JDBCUserFactory", e);
        }
    }

    /**
     * Guest can register in system by providing the information needed and if the email is not already present
     * in the database then the user is created
     *
     * @param user all information needed to create a user
     */
    @Override
    public void createUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_CREATE"))){
            statement.setString(1, user.getRole().name());
            statement.setString(2, user.getEnFirstName());
            statement.setString(3, user.getUaFirstName());
            statement.setString(4, user.getEnLastName());
            statement.setString(5, user.getUaLastName());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPassword());
            statement.setInt(8, user.getAge());
            statement.setString(9, user.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: createuser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: createuser", e);
            }
        }
    }

    @Override
    public User getUserRegistrationInfo(Long userId) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_FIND_BY_ID"))){
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: createuser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: createuser", e);
            }
        }
        return user;
    }

    @Override
    public List<TestsWithResultsDto> getUserTests(Long userId) {
        Map<Long, TestsWithResultsDto> TestsWithResultsDtos = new HashMap<>();
        Map<Long, Subject> subjects = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_SELECT_WITH_SUBJECT_AND_MARK"))) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            TestsWithResultsDtoMapper testsWithResultsDtoMapper = new TestsWithResultsDtoMapper();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                TestsWithResultsDto testsWithResultsDto = testsWithResultsDtoMapper.extractFromResultSet(resultSet);
                testsWithResultsDto = testsWithResultsDtoMapper.makeUnique(TestsWithResultsDtos, testsWithResultsDto);
                Subject subject= subjectMapper.extractFromResultSet(resultSet);
                subject = subjectMapper.makeUnique(subjects, subject);
                testsWithResultsDto.setSubject(subject);
            }

        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: createuser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: createuser", e);
            }
        }
        return new ArrayList<>(TestsWithResultsDtos.values());
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_FIND_BY_EMAIL"))){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: findByEmail", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: findByEmail", e);
            }
        }
        return user;
    }

    @Override
    public void blockUser(Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_BlOCK"))) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: blockUser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: blockUser", e);
            }
        }
    }

    @Override
    public void unblockUser(Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_UNBlOCK"))) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: unblockUser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: unblockUser", e);
            }
        }
    }

    @Override
    public void editUser (User user, List<Long> testIds) {
        try (PreparedStatement statement1 = connection.prepareStatement(properties.getProperty("USER_TEST_INSERT"));
             PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("USER_UPDATE"))){
            connection.setAutoCommit(false);
            for (Long id : testIds) {
                statement1.setLong(2, id);
                statement1.setLong(1, user.getId());
                statement1.executeUpdate();
            }
            statement2.setLong(9, user.getId());
            statement2.setString(1, user.getEnFirstName());
            statement2.setString(2, user.getUaFirstName());
            statement2.setString(3, user.getEnLastName());
            statement2.setString(4, user.getUaLastName());
            statement2.setString(5, user.getEmail());
            statement2.setString(6, user.getPassword());
            statement2.setInt(7, user.getAge());
            statement2.setString(8, user.getPhone());
            statement2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: updateuser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: updateuser", e);
            }
        }
    }

    @Override
    public List<Question> testSelection(Long userId, Long testId) {
        Map<Long, Question> questions = new HashMap<>();
        try (PreparedStatement statement1 = connection.prepareStatement(properties.getProperty("TEST_SELECT_START_DATE"));
             PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("QUESTION_FIND_BY_TEST_ID"));
                PreparedStatement statement3 = connection.prepareStatement(properties.getProperty("USER_TEST_SELECT_TEST_ID"));
                PreparedStatement statement4 = connection.prepareStatement(properties.getProperty("USER_TEST_INSERT"))) {
            connection.setAutoCommit(false);
            List<Long> testIds = new ArrayList<>();
            LocalDateTime passingTestDate = LocalDateTime.now();

            statement1.setLong(1, testId);
            ResultSet resultSet1 = statement1.executeQuery();
            while(resultSet1.next()) {
                LocalDateTime testStartDate = resultSet1.getTimestamp("test.test_date").toLocalDateTime();
                if (passingTestDate.isAfter(testStartDate)) {
                statement2.setLong(1, testId);
                ResultSet resultSet2 = statement2.executeQuery();
                QuestionMapper questionMapper = new QuestionMapper();
                while(resultSet2.next()) {
                   Question question = questionMapper.extractFromResultSet(resultSet2);
                   questionMapper.makeUnique(questions, question);
            }
                    statement3.setLong(1, userId);
                    ResultSet resultSet3 = statement3.executeQuery();
                    while (resultSet3.next()) {
                        Long testId1 = resultSet3.getLong("test_id");
                        testIds.add(testId1);
                    }
                    if (!testIds.contains(testId)) {
                        statement4.setLong(2, testId);
                        statement4.setLong(1, userId);
                        statement4.executeUpdate();
                    }
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: testSelection", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: testSelection", e);
            }
        }
        return new ArrayList<>(questions.values());
    }

    @Override
    public void testPassing(Long userId, Long testId, List<List<Long>> studentAnswers) {

        try (PreparedStatement statement1 = connection.prepareStatement(properties.getProperty("QUESTION_FIND_BY_TEST_ID"));
             PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("ANSWER_SELECT_BY_QUESTION_ID"));
             PreparedStatement statement3 = connection.prepareStatement(properties.getProperty("TEST_FIND_BY_ID"));
             PreparedStatement statement4 = connection.prepareStatement(properties.getProperty("USER_UPDATE_MARK"))) {
            connection.setAutoCommit(false);
            List<Question> questions = new ArrayList<>();
            int correctQuestions = 0;
            List<List<Long>> correctAnswers = new ArrayList<>();

            statement1.setLong(1, testId);
            ResultSet resultSet1 = statement1.executeQuery();
            QuestionMapper questionMapper = new QuestionMapper();
            while(resultSet1.next()) {
                Question question = questionMapper.extractFromResultSet(resultSet1);
                questions.add(question);
            }
            for(Question question : questions){
                List<Long> answers1 = new ArrayList<>();
                List<Answer> answers2 = new ArrayList<>();
                Long answerOrder = 0L;
                statement2.setLong(1, question.getId());
                ResultSet resultSet2 = statement2.executeQuery();
                AnswerMapper answerMapper = new AnswerMapper();
                while(resultSet2.next()) {
                    Answer answer = answerMapper.extractFromResultSet(resultSet2);
                    answers2.add(answer);
                }
                for (Answer answer : answers2){
                    ++ answerOrder;
                    if (answer.getCorrectnessState()){
                        answers1.add(answerOrder);
                    }
                }
                correctAnswers.add(answers1);
            }
            for(int i = 0; i < studentAnswers.size();i++){
                int answerOrder = 0;
                for(Long studentAnswer : studentAnswers.get(i)){
                    ++ answerOrder;
                    if(!correctAnswers.get(i).contains(studentAnswer)){
                        break;
                    }
                    if(correctAnswers.get(i).size() == answerOrder) {
                        ++ correctQuestions;
                    }
                }
            }
            statement3.setLong(1, testId);
            ResultSet resultSet3 = statement3.executeQuery();
            TestMapper testMapper = new TestMapper();
            while(resultSet3.next()) {
                Test test = testMapper.extractFromResultSet(resultSet3);
                int mark = correctQuestions * 100 / test.getQuestionAmount() ;
                statement4.setInt(1, mark);
                statement4.setLong(2, userId);
                statement4.setLong(3, testId);
                statement4.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: testPassing", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: testPassing", e);
            }
        }
    }
}
