package model.dao.mapper;

import model.dto.TestsWithResultsDto;
import model.entity.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class TestsWithResultsDtoMapper implements ObjectMapper<TestsWithResultsDto>{
    @Override
    public TestsWithResultsDto extractFromResultSet(ResultSet resultSet) throws SQLException {
        TestsWithResultsDto testsWithResultsDto = new TestsWithResultsDto();

        testsWithResultsDto.setTestId(resultSet.getLong("test.id"));
        testsWithResultsDto.setTestEnName(resultSet.getString("test.name_en"));
        testsWithResultsDto.setTestUaName(resultSet.getString("test.name_ua"));
        testsWithResultsDto.setMark(resultSet.getInt("user_test.mark"));

        return testsWithResultsDto;
    }

    @Override
    public TestsWithResultsDto makeUnique(Map<Long, TestsWithResultsDto> cache, TestsWithResultsDto entity) {
        cache.putIfAbsent(entity.getTestId(), entity);
        return cache.get(entity.getTestId());
    }
}
