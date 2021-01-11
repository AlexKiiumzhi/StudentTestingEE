package model.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private Long id;
    @Pattern(regexp="[A-Za-z]{3,100}", message = "Field english name must be a minimum length of 3!")
    @NotEmpty(message = "Field english name must not be empty!")
    private String enName;
    @Pattern(regexp="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{3,100}", message = "Field ukrainian Name  must be a minimum length of 3 and only ukrainian letters!")
    @NotEmpty(message = "Field ukrainian Name must not be empty!")
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
