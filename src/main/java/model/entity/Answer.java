package model.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

public class Answer {

    private Long id;
    @Pattern(regexp="[A-Za-z]{3,100}", message = "Field english description must be a minimum length of 3!")
    @NotEmpty(message = "Field english description must not be empty!")
    private String enAnswer;
    @Pattern(regexp="[А-ЩЬЮЯҐЄІЇа-щьюяґєії]{3,100}", message = "Field ukrainian description must be a minimum length of 3 and only ukrainian letters!")
    @NotEmpty(message = "Field ukrainian description must not be empty!")
    private String uaAnswer;
    private Boolean correctnessState;
    private Question question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnAnswer() {
        return enAnswer;
    }

    public void setEnAnswer(String enAnswer) {
        this.enAnswer = enAnswer;
    }

    public String getUaAnswer() {
        return uaAnswer;
    }

    public void setUaAnswer(String uaAnswer) {
        this.uaAnswer = uaAnswer;
    }

    public Boolean getCorrectnessState() {
        return correctnessState;
    }

    public void setCorrectnessState(Boolean correctnessState) {
        this.correctnessState = correctnessState;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
