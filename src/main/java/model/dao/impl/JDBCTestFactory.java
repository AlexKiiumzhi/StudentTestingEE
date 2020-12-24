package model.dao.impl;

import model.dao.TestDao;
import model.dao.mapper.QuestionMapper;
import model.dao.mapper.SubjectMapper;
import model.dao.mapper.TestMapper;
import model.entity.Answer;
import model.entity.Question;
import model.entity.Subject;
import model.entity.Test;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCTestFactory implements TestDao {

    static final Logger logger = Logger.getLogger(JDBCUserFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCTestFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCTestFactory: JDBCTestFactory", e);
        }
    }

    @Override
    public List<Test> findAllPagination(int startIndex, int recordPerPage) {
        Map<Long, Test> tests = new HashMap<>();
        Map<Long, Subject> subjects = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_SELECT_ALL_PAGINATION"))){
            statement.setInt(1, startIndex);
            statement.setInt(2, recordPerPage);
            ResultSet resultSet = statement.executeQuery();
            TestMapper testMapper = new TestMapper();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Test test = testMapper.extractFromResultSet(resultSet);
                test = testMapper.makeUnique(tests, test);
                Subject subject= subjectMapper.extractFromResultSet(resultSet);
                subject = subjectMapper.makeUnique(subjects, subject);
                test.setSubject(subject);
            }
        } catch (SQLException e) {
            logger.error("SQLException in JDBCTestFactory: findAllPagination", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCTestFactory: findAllPagination", e);
            }
        }
        return new ArrayList<>(tests.values());
    }

    @Override
    public int numberOfRows() {
        int totalNumberOfRecords = 0;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_NUMBER_OF_ROWS"))) {
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                totalNumberOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("SQLException in JDBCTestFactory: numberOfRows", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCTestFactory: numberOfRows", e);
            }
        }
        return totalNumberOfRecords;
    }

    @Override
    public List<Test> findBySubject(Long subjectId) {
        Map<Long, Test> tests = new HashMap<>();
        Map<Long, Subject> subjects = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FIND_TEST_BY_SUBJECT"))) {
            statement.setLong(1, subjectId);
            ResultSet resultSet = statement.executeQuery();
            TestMapper testMapper = new TestMapper();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Test test = testMapper.extractFromResultSet(resultSet);
                test = testMapper.makeUnique(tests, test);
                Subject subject= subjectMapper.extractFromResultSet(resultSet);
                subject = subjectMapper.makeUnique(subjects, subject);
                test.setSubject(subject);
            }
        } catch (SQLException e) {
            logger.error("SQLException in JDBCTestFactory: findBySubject", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCTestFactory: findBySubject", e);
            }
        }
        return new ArrayList<>(tests.values());
    }

    @Override
    public void createTest (Test test, Long subjectId) {
        try (PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("TEST_CREATE"))){
            statement2.setString(1, test.getEnName());
            statement2.setString(2, test.getUaName());
            statement2.setLong(3, test.getDifficulty());
            statement2.setInt(4, test.getQuestionAmount());
            statement2.setTimestamp(5, Timestamp.valueOf(test.getTestDate()));
            statement2.setLong(6, subjectId);
            statement2.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: create", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: create", e);
            }
        }
    }

    @Override
    public void editTest (Test test) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_UPDATE"))){
            statement.setLong(6, test.getId());
            statement.setString(1, test.getEnName());
            statement.setString(2, test.getUaName());
            statement.setLong(3, test.getDifficulty());
            statement.setInt(4, test.getQuestionAmount());
            statement.setTimestamp(5, Timestamp.valueOf(test.getTestDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: create", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: create", e);
            }
        }
    }

    @Override
    public void deleteTest (Long testId) {
        List <Long> questionIds = new ArrayList<>();
        try (PreparedStatement statement1 = connection.prepareStatement(properties.getProperty("QUESTION_ID.FIND_BY_ID"));
             PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("ANSWER_DELETE"));
             PreparedStatement statement3 = connection.prepareStatement(properties.getProperty("QUESTION_DELETE"));
             PreparedStatement statement4 = connection.prepareStatement(properties.getProperty("TEST_DELETE"))){
            statement1.setLong(1, testId);
            ResultSet resultSet1 = statement1.executeQuery();
            while(resultSet1.next()) {
                Long questionId = resultSet1.getLong("question.id");
                questionIds.add(questionId);
            }
            for(Long id: questionIds) {
                statement2.setLong(1, id);
                statement2.executeUpdate();
            }
            statement3.setLong(1, testId);
            statement3.executeUpdate();
            statement4.setLong(1, testId);
            statement4.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: create", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: create", e);
            }
        }
    }

    /*@Override
    public List<Test> findAllTestsByName() {
        Map<Long, Test> tests = new HashMap<>();


        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("FIND_TEST_BY_NAME"))) {
            ResultSet resultSet = statement.executeQuery();
            TestMapper testMapper = new TestMapper();
            while(resultSet.next()) {
                Test test = testMapper.extractFromResultSet(resultSet);
                testMapper.makeUnique(tests, test);

            }
        } catch (SQLException e) {
            logger.error("SQLException in JDBCTestFactory: findAllByName", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCTestFactory: findBySubject", e);
            }
        }
        return new ArrayList<>(tests.values());
    }*/
}
