package model.entity;

import model.entity.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private Role role;
    private String enFirstName;
    private String uaFirstName;
    private String enLastName;
    private String uaLastName;
    private String email;
    private String password;
    private int age;
    private String phone;
    private Boolean isBlocked;
    private List<Test> tests = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEnFirstName() {
        return enFirstName;
    }

    public void setEnFirstName(String enFirstName) {
        this.enFirstName = enFirstName;
    }

    public String getUaFirstName() {
        return uaFirstName;
    }

    public void setUaFirstName(String uaFirstName) {
        this.uaFirstName = uaFirstName;
    }

    public String getEnLastName() {
        return enLastName;
    }

    public void setEnLastName(String enLastName) {
        this.enLastName = enLastName;
    }

    public String getUaLastName() {
        return uaLastName;
    }

    public void setUaLastName(String uaLastName) {
        this.uaLastName = uaLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
