package model.dao.mapper;

import model.entity.Question;
import model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class QuestionMapper implements ObjectMapper<Question>{

    @Override
    public Question extractFromResultSet(ResultSet resultSet) throws SQLException {
        Question question = new Question();

        question.setId(resultSet.getLong("question.id"));
        question.setEnText(resultSet.getString("question.text_en"));
        question.setUaText(resultSet.getString("question.text_ua"));
        question.setPass(resultSet.getBoolean("question.pass"));
        return question;
    }

    @Override
    public Question makeUnique(Map<Long, Question> cache, Question entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
