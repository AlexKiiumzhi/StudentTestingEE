package model.dao;

import model.entity.Answer;

import java.util.List;

public interface AnswerDao {
    void createAnswer (Answer answer, Long questionId);
    void editAnswer (Answer answer);
    List<Answer> findAll();
}
