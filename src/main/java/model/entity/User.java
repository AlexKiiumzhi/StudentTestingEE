package model.entity;

import model.entity.enums.Role;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class User {


    private Long id;
    private Role role;
    @Pattern(regexp="[A-Za-z]{3,100}", message = "Field english first name must be a minimum length of 3!")
    @NotEmpty(message = "Field english first name must not be empty!")
    private String enFirstName;
    @Pattern(regexp="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{3,100}", message = "Field english last name must be a minimum length of 3 and only ukrainian letters!")
    @NotEmpty(message = "Field english last name must not be empty!")
    private String uaFirstName;
    @Pattern(regexp="[A-Za-z]{3,100}", message = "Field  ukrainian Last name must be a minimum length of 3!")
    @NotEmpty(message = "Field ukrainian Last name must not be empty!")
    private String enLastName;
    @Pattern(regexp="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{3,100}", message = "Field ukrainian last name must be a minimum length of 3 and only ukrainian letters!")
    @NotEmpty(message = "Field ukrainian last name must not be empty!")
    private String uaLastName;
    @NotEmpty(message = "Field email must not be empty!")
    @Email()
    private String email;
    @NotNull
    @NotEmpty(message = "Field password must not be empty!")
    @Pattern(regexp="[A-Z0-9a-z]{8,30}", message = "Field password must be a minimum length of 8 and only english letters!")
    @Size(min = 8, max = 50 , message = " ")
    private String password;
    private int age;
    @Pattern(regexp="[0-9]{10,20}", message = "Field phone must be a minimum length of 10 and only numbers!")
    @NotEmpty(message = "Field phone must not be empty!")
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
