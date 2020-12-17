package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private Long id;
    private String enText;
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
