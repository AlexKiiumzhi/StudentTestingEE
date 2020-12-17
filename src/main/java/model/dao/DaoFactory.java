package model.dao;

import model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract SubjectDao createSubjectDao();
    public abstract TestDao createTestDao();
    public abstract QuestionDao createQuestionDao();
    public abstract AnswerDao createAnswerDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}