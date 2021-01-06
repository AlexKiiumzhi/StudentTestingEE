package model.dao.impl;

import model.dao.QuestionDao;
import model.dao.mapper.AnswerMapper;
import model.dao.mapper.TestMapper;
import model.entity.Answer;
import model.entity.Question;
import model.entity.Test;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCQuestionFactory implements QuestionDao {

    static final Logger logger = Logger.getLogger(JDBCUserFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCQuestionFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCQuestionFactory: JDBCQuestionFactory", e);
        }
    }

    @Override
    public void createQuestion (Question question, Long testId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("QUESTION_CREATE"))){
            statement.setString(1, question.getEnText());
            statement.setString(2, question.getUaText());
            statement.setLong(3, testId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCQuestionFactory: create", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCQuestionFactory: create", e);
            }
        }
    }

    @Override
    public void editQuestion (Question question, List<Long> answerIds) {

        try (PreparedStatement statement1 = connection.prepareStatement(properties.getProperty("ANSWER_DELETE_QUESTION_ID"));
                PreparedStatement statement2 = connection.prepareStatement(properties.getProperty("ANSWER_UPDATE_QUESTION_ID"));
             PreparedStatement statement3 = connection.prepareStatement(properties.getProperty("QUESTION_UPDATE"))) {
            connection.setAutoCommit(false);
            if (answerIds.size() != 0) {
                statement1.setLong(1, question.getId());
                statement1.executeUpdate();
            }
            for (Long id : answerIds) {
                statement2.setLong(2, id);
                statement2.setLong(1, question.getId());
                statement2.executeUpdate();
            }
            statement3.setLong(3, question.getId());
            statement3.setString(1, question.getEnText());
            statement3.setString(2, question.getUaText());
            statement3.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.error("SQLException in JDBCQuestionFactory: update", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCQuestionFactory: update", e);
            }
        }
    }
}
