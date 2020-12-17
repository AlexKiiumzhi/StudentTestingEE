package model.dao;

import model.entity.User;

public interface UserDao {

    public void create (User user);
    public User findByEmail(String email);
    public void close();
}
