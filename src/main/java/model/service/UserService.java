package model.service;

import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entity.User;

import java.util.regex.Pattern;

public class UserService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createUser(User user) {
        UserDao dao = daoFactory.createUserDao();
        dao.createUser(user);
    }

    public User findByEmail(String email) {
        UserDao dao = daoFactory.createUserDao();
        User user =  dao.findByEmail(email);
        return user;
    }

    public void blockUser(Long userId){
        UserDao dao = daoFactory.createUserDao();
        dao.blockUser(userId);
    }

    public void unblockUser(Long userId){
        UserDao dao = daoFactory.createUserDao();
        dao.unblockUser(userId);
    }
    public boolean validateNullRegistration(User user, String age) {
        if (user.getEnFirstName() == null || user.getEnLastName() == null || user.getUaFirstName() == null ||
                user.getUaLastName() == null || user.getEmail() == null || user.getPassword() == null ||
                user.getPhone() == null || age == null || age.equals("") || user.getEnFirstName().equals("") ||
                user.getEnLastName().equals("") ||  user.getUaFirstName().equals("") || user.getUaLastName().equals("") ||
                user.getEmail().equals("") || user.getPassword().equals("") || user.getPhone().equals("")) {
            return false;
        }
        return true;
    }

    public boolean validateRegistration(User user, String age) {
        boolean checkEnFirstName = Pattern.compile("^[a-zA-Z]+").matcher(user.getEnFirstName()).matches();
        boolean checkEnLastName = Pattern.compile("^[a-zA-Z]+").matcher(user.getEnLastName()).matches();
        boolean checkUaFirstName = Pattern.compile("[А-ЩЬЮЯҐЄІЇа-щьюяґєії']+").matcher(user.getUaFirstName()).matches();
        boolean checkUaLastName = Pattern.compile("[А-ЩЬЮЯҐЄІЇа-щьюяґєії']+").matcher(user.getUaLastName()).matches();
        boolean checkEmail = Pattern.compile(".{1,50}@[a-zA-z]{1,15}\\.[a-zA-Z]{1,5}").matcher(user.getEmail()).matches();
        boolean checkAge = Pattern.compile("^(100|[1-9][0-9]?)$").matcher(age).matches();
        boolean checkPhone = Pattern.compile("0\\d{9}").matcher(user.getPhone()).matches();
        if (!checkEnFirstName || !checkEnLastName || !checkUaFirstName || !checkUaLastName || !checkEmail ||
                !checkAge || !checkPhone) {
            return false;
        }
        return true;
    }

    public boolean validateNullLogin(String email, String password) {
        if (email == null || email.equals("") || password == null || password.equals("")) {
            return false;
        }
        return true;
    }
}
