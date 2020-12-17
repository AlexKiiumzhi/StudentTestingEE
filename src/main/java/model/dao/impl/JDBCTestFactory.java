package model.dao.impl;

import model.dao.TestDao;
import model.dao.mapper.SubjectMapper;
import model.dao.mapper.TestMapper;
import model.entity.Subject;
import model.entity.Test;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JDBCTestFactory implements TestDao {

    static final Logger logger = Logger.getLogger(JDBCUserFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCTestFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\db.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCTestFactory: JDBCTestFactory", e);
        }
    }

    @Override
    public List<Test> findAllPagination(int startIndex, int recordPerPage) {
        Map<Long, Test> tests = new HashMap<>();
        Map<Long, Subject> subjects = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SPECIALITY_SELECT_ALL_PAGINATION"))){
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
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SPECIALITY_NUMBER_OF_ROWS"))) {
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
}
