package model.dto;

import model.entity.Subject;

public class TestsWithResultsDto {

    private Long testId;
    private String testEnName;
    private String testUaName;
    private Subject subject;
    private Integer mark;

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTestEnName() {
        return testEnName;
    }

    public void setTestEnName(String testEnName) {
        this.testEnName = testEnName;
    }

    public String getTestUaName() {
        return testUaName;
    }

    public void setTestUaName(String testUaName) {
        this.testUaName = testUaName;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
