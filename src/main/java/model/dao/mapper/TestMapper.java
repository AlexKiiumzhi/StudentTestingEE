package model.dao.mapper;


import model.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TestMapper implements ObjectMapper<Test> {
    @Override
    public Test extractFromResultSet(ResultSet resultSet) throws SQLException {
        Test test = new Test();

        test.setId(resultSet.getLong("test.id"));
        test.setEnName(resultSet.getString("test.name_en"));
        test.setUaName(resultSet.getString("test.name_ua"));
        test.setDifficulty(resultSet.getLong("test.difficulty"));
        test.setQuestionAmount(resultSet.getInt("test.question_amount"));
        test.setTestDate(resultSet.getTimestamp("test.test_date").toLocalDateTime());

        return test;
    }
    @Override
    public Test makeUnique(Map<Long, Test> cache, Test entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}