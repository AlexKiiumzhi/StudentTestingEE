package model.dao.impl;

import model.dao.AnswerDao;
import model.dao.mapper.AnswerMapper;
import model.dao.mapper.SubjectMapper;
import model.entity.Answer;
import model.entity.Subject;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBCAnswerFactory implements AnswerDao {

    static final Logger logger = Logger.getLogger(JDBCUserFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCAnswerFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCAnswerFactory: JDBCAnswerFactory", e);
        }
    }

    @Override
    public void createAnswer (Answer answer, Long questionId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("ANSWER_CREATE"))){
            statement.setString(1, answer.getEnAnswer());
            statement.setString(2, answer.getUaAnswer());
            statement.setBoolean(3, answer.getCorrectnessState());
            statement.setLong(4, questionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCAnswerFactory: create", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCAnswerFactory: create", e);
            }
        }
    }

    @Override
    public void editAnswer (Answer answer) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("ANSWER_UPDATE"))){
            statement.setLong(4, answer.getId());
            statement.setString(1, answer.getEnAnswer());
            statement.setString(2, answer.getUaAnswer());
            statement.setBoolean(3, answer.getCorrectnessState());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCAnswerFactory: update", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCAnswerFactory: update", e);
            }
        }
    }

    @Override
    public List<Answer> findAll() {
        Map<Long, Answer> answers = new HashMap<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(properties.getProperty("ANSWER_SELECT_ALL"));
            AnswerMapper answerMapper = new AnswerMapper();
            while(resultSet.next()) {
                Answer answer = answerMapper.extractFromResultSet(resultSet);
                answerMapper.makeUnique(answers, answer);
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
        return new ArrayList<>(answers.values());
    }
}
