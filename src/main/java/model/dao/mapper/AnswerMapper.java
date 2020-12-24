package model.dao.mapper;

import model.entity.Answer;
import model.entity.Question;
import model.entity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class AnswerMapper implements ObjectMapper<Answer>{
    @Override
    public Answer extractFromResultSet(ResultSet resultSet) throws SQLException {
        Answer answer = new Answer();

        answer.setId(resultSet.getLong("subject.id"));
        answer.setEnAnswer(resultSet.getString("answer.answer_en"));
        answer.setUaAnswer(resultSet.getString("answer.answer_ua"));
        answer.setCorrectnessState(resultSet.getBoolean("answer.correctness_state"));
        return answer;
    }

    @Override
    public Answer makeUnique(Map<Long, Answer> cache, Answer entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
