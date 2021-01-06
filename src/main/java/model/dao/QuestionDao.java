package model.dao;

import model.entity.Question;

import java.util.List;

public interface QuestionDao {
    void createQuestion (Question question, Long testId);
    void editQuestion (Question question, List<Long> answerIds);
}
