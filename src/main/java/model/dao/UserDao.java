package model.dao;

import model.entity.User;

public interface UserDao {

    void createUser(User user);
    User findByEmail(String email);
    void blockUser(Long userId);
    void unblockUser(Long userId);
    void close();
}
