package model.entity;

public class Answer {

    private Long id;
    private String enAnswer;
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
