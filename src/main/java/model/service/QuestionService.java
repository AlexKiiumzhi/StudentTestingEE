package model.service;

import model.dao.DaoFactory;
import model.dao.QuestionDao;
import model.entity.Question;

import java.util.List;


public class QuestionService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createQuestion(Question question , Long testId) {
        QuestionDao dao = daoFactory.createQuestionDao();
        dao.createQuestion(question, testId);
    }

    public void editQuestion(Question question, List<Long> answerIds) {
        QuestionDao dao = daoFactory.createQuestionDao();
        dao.editQuestion(question, answerIds);
    }
}
