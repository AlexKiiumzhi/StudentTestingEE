package model.service;

import model.dao.DaoFactory;
import model.dao.TestDao;
import model.dao.UserDao;
import model.entity.Test;
import model.entity.User;


import java.util.List;

public class TestService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Test> findAllPagination(int startIndex, int recordPerPage) {
        TestDao dao = daoFactory.createTestDao();
        return dao.findAllPagination(startIndex, recordPerPage);
    }

    public int numberOfRows() {
        TestDao dao = daoFactory.createTestDao();
        return dao.numberOfRows();
    }

    public List<Test> findBySubject(Long subjectId) {
        TestDao dao = daoFactory.createTestDao();
        return dao.findBySubject(subjectId);
    }

    public void createTest(Test test , Long subjectId) {
        TestDao dao = daoFactory.createTestDao();
        dao.createTest(test, subjectId);
    }

    public void editTest(Test test) {
        TestDao dao = daoFactory.createTestDao();
        dao.editTest(test);
    }

    public void deleteTest(Long testID) {
        TestDao dao = daoFactory.createTestDao();
        dao.deleteTest(testID);
    }
   /* public List<Test> findAllByName() {
        TestDao dao = daoFactory.createTestDao();
        return dao.findAllTestsByName();
    }*/
}