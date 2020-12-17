package model.dao.impl;

import model.dao.SubjectDao;
import model.dao.mapper.SubjectMapper;
import model.entity.Subject;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCSubjectFactory implements SubjectDao {

    static final Logger logger = Logger.getLogger(JDBCUserFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCSubjectFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\db.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCSubjectFactory: JDBCSubjectFactory", e);
        }
    }

    @Override
    public Subject findById(int id) {
        Subject subject = new Subject();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("SUBJECT_FIND_BY_ID"))) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                subject = subjectMapper.extractFromResultSet(resultSet);
            }
            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Subject> findAll() {
        Map<Long, Subject> subjects = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("SUBJECT_SELECT_ALL"));
            SubjectMapper subjectMapper = new SubjectMapper();
            while(resultSet.next()) {
                Subject subject = subjectMapper.extractFromResultSet(resultSet);
                subjectMapper.makeUnique(subjects, subject);
            }
        } catch (SQLException e) {
            logger.error("IOException in JDBCSpecialityFactory: findAll", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("IOException in JDBCSpecialityFactory: findAll", e);
            }
        }
        return new ArrayList<>(subjects.values());
    }
}
