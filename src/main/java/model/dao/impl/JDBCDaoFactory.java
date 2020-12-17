package model.dao.impl;

import model.dao.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    static final Logger logger = Logger.getLogger(JDBCDaoFactory.class);
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCDaoFactory: getConnection", e);
            throw new RuntimeException();
        }
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserFactory(getConnection());
    }

    @Override
    public SubjectDao createSubjectDao() {
        return new JDBCSubjectFactory(getConnection());
    }

    @Override
    public TestDao createTestDao() {
        return new JDBCTestFactory(getConnection());
    }

    @Override
    public QuestionDao createQuestionDao() {
        return new JDBCQuestionFactory(getConnection());
    }

    @Override
    public AnswerDao createAnswerDao() {
        return new JDBCAnswerFactory(getConnection());
    }
}