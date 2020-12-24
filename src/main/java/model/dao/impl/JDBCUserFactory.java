package model.dao.impl;

import model.dao.UserDao;
import model.dao.mapper.UserMapper;
import model.entity.User;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUserFactory implements UserDao {

    static final Logger logger = Logger.getLogger(JDBCUserFactory.class);
    private Connection connection;
    private Properties properties;

    public JDBCUserFactory(Connection connection) {
        this.connection = connection;
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Sasha\\Work\\Training\\FinalProject\\JavaEE\\StudentTesting\\src\\main\\resources\\sql.properties"));
        } catch (IOException e) {
            logger.error("IOException in JDBCUserFactory: JDBCUserFactory", e);
        }
    }

    @Override
    public void createUser(User user) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_CREATE"))){
            statement.setString(1, user.getRole().name());
            statement.setString(2, user.getEnFirstName());
            statement.setString(3, user.getUaFirstName());
            statement.setString(4, user.getEnLastName());
            statement.setString(5, user.getUaLastName());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getPassword());
            statement.setInt(8, user.getAge());
            statement.setString(9, user.getPhone());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: create", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: create", e);
            }
        }
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_FIND_BY_EMAIL"))){
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            UserMapper userMapper = new UserMapper();
            if (resultSet.next()) {
                user = userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: findByEmail", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: findByEmail", e);
            }
        }
        return user;
    }

    @Override
    public void blockUser(Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_BlOCK"))) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: blockUser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: blockUser", e);
            }
        }
    }

    @Override
    public void unblockUser(Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("USER_UNBlOCK"))) {
            statement.setLong(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: blockUser", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("SQLException in JDBCUserFactory: blockUser", e);
            }
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("SQLException in JDBCUserFactory: close", e);
            throw new RuntimeException(e);
        }
    }
}
