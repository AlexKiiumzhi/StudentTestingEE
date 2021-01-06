package model.service;

import model.dao.AnswerDao;
import model.dao.DaoFactory;
import model.dao.QuestionDao;
import model.dao.SubjectDao;
import model.entity.Answer;
import model.entity.Question;
import model.entity.Subject;

import java.util.List;

public class AnswerService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public void createAnswer(Answer answer , Long questionId) {
        AnswerDao dao = daoFactory.createAnswerDao();
        dao.createAnswer(answer, questionId);
    }

    public void editAnswer(Answer answer) {
        AnswerDao dao = daoFactory.createAnswerDao();
        dao.editAnswer(answer);
    }

    public List<Answer> findAll() {
        AnswerDao dao = daoFactory.createAnswerDao();
        return dao.findAll();
    }
}
