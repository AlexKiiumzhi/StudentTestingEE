package model.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private Long id;
    @Pattern(regexp="[A-Za-z]{3,100}", message = "Field english description must be a minimum length of 3!")
    @NotEmpty(message = "Field english description must not be empty!")
    private String enText;
    @Pattern(regexp="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{3,100}", message = "Field ukrainian description must be a minimum length of 3 and only ukrainian letters!")
    @NotEmpty(message = "Field ukrainian description must not be empty!")
    private String uaText;
    private Boolean pass;
    private Test test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnText() {
        return enText;
    }

    public void setEnText(String enText) {
        this.enText = enText;
    }

    public String getUaText() {
        return uaText;
    }

    public void setUaText(String uaText) {
        this.uaText = uaText;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
