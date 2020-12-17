package model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private Long id;
    private String enName;
    private String uaName;
    private Long difficulty;
    private Integer questionAmount;
    private LocalDateTime testDate;
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getUaName() {
        return uaName;
    }

    public void setUaName(String uaName) {
        this.uaName = uaName;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getQuestionAmount() {
        return questionAmount;
    }

    public void setQuestionAmount(Integer questionAmount) {
        this.questionAmount = questionAmount;
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
