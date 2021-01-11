package model.dao.impl;

import model.dao.TestDao;
import model.dao.mapper.SubjectMapper;
import model.dao.mapper.TestMapper;
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

    /**
     * Retrieves All tests and the results are divided into pages by the pagination process
     *
     * @param (startIndex, recordPerPage) necessary information for pagination
     * @return List<Test> List of Tests
     */
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

    /**
     * Helps with the pagination process by counting how many rows or tests there is in database
     *
     * @return Int number of rows
     */
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

    /**
     * Implements a filtering process for tests to be shown
     *
     * @param subjectId
     * @return List<Test> All tests of a specific subject
     */
    @Override
    public List<Test> findBySubject(Long subjectId) {
        Map<Long, Test> tests = new HashMap<>();
        Map<Long, Subject> subjects = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_FIND_BY_SUBJECT"))) {
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

    /**
     * Admin can create a test by providing the information needed then it is saved
     *
     * @param (test, subjectID) all information needed to create a test
     */
    @Override
    public void createTest (Test test, Long subjectId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_CREATE"))){
            statement.setString(1, test.getEnName());
            statement.setString(2, test.getUaName());
            statement.setLong(3, test.getDifficulty());
            statement.setInt(4, test.getQuestionAmount());
            statement.setTimestamp(5, Timestamp.valueOf(test.getTestDate()));
            statement.setLong(6, subjectId);
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

    /**
     * Admin can edit a test by providing the information needed then it is saved
     *
     * @param test object that will update the existing one
     */
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
            logger.error("SQLException in JDBCUserFactory: update", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: update", e);
            }
        }
    }

    /**
     * Admin can delete a test by providing the information needed then the changes are saved in database
     *
     * @param testId Id of test that will be deleted
     */
    @Override
    public void deleteTest (Long testId) {
        List <Long> questionIds = new ArrayList<>();
        try (PreparedStatement statement1 = connection.prepareStatement(properties.getProperty("QUESTION_ID_FIND_BY_TEST_ID"));
             PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("ANSWER_DELETE"));
             PreparedStatement statement3 = connection.prepareStatement(properties.getProperty("QUESTION_DELETE"));
             PreparedStatement statement4 = connection.prepareStatement(properties.getProperty("TEST_DELETE"))){
            connection.setAutoCommit(false);
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
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: delete", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: delete", e);
            }
        }
    }

    /**
     * Retrieves all tests from database
     *
     * @return List<Test>
     */
    @Override
    public List<Test> findAll() {
        Map<Long, Test> tests = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_SELECT_ALL"))) {
            ResultSet resultSet = statement.executeQuery();
            TestMapper testMapper = new TestMapper();
            while(resultSet.next()) {
                Test test = testMapper.extractFromResultSet(resultSet);
                testMapper.makeUnique(tests, test);
            }
        } catch (SQLException e) {
            logger.error("IOException in JDBCAnswerFactory: findAll", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("IOException in JDBCAnswerFactory: findAll", e);
            }
        }
        return new ArrayList<>(tests.values());
    }

    /**
     * Retrieves all tests with respective subjects from database
     *
     * @return List<Test>
     */
    @Override
    public List<Test> sortTests() {
        Map<Long, Test> tests = new HashMap<>();
        Map<Long, Subject> subjects = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("TEST_FIND_ALL_WITH_SUBJECTS"))) {
            ResultSet resultSet = statement.executeQuery();
            TestMapper testMapper = new TestMapper();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Test test = testMapper.extractFromResultSet(resultSet);
                testMapper.makeUnique(tests, test);
                Subject subject= subjectMapper.extractFromResultSet(resultSet);
                subject = subjectMapper.makeUnique(subjects, subject);
                test.setSubject(subject);
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
    }
}
