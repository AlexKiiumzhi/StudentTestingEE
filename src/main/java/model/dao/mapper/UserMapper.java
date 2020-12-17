package model.dao.mapper;

import model.entity.User;
import model.entity.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();

        user.setId(resultSet.getLong("user.id"));
        user.setRole(Role.valueOf(resultSet.getString("user.role").toUpperCase()));
        user.setEnFirstName(resultSet.getString("user.first_name_en"));
        user.setUaFirstName(resultSet.getString("user.first_name_ua"));
        user.setEnLastName(resultSet.getString("user.last_name_en"));
        user.setUaLastName(resultSet.getString("user.last_name_ua"));
        user.setEmail(resultSet.getString("user.email"));
        user.setPassword(resultSet.getString("user.password"));
        user.setAge(resultSet.getInt("user.age"));
        user.setPhone(resultSet.getString("user.phone"));

        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}