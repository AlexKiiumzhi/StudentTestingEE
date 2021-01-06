package model.service;

import model.dao.DaoFactory;
import model.dao.TestDao;
import model.entity.Test;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Test> findAll() {
        TestDao dao = daoFactory.createTestDao();
        return dao.findAll();
    }
    public List<Test> sortTests(String sortingParameter) {
        TestDao dao = daoFactory.createTestDao();
        List<Test> tests = dao.sortTests();
        switch (sortingParameter){
            case "enName":
                tests = tests.stream()
                        .sorted(Comparator.comparing(Test::getEnName)).collect(Collectors.toList());
                break;
            case "uaName":
                tests = tests.stream()
                        .sorted(Comparator.comparing(Test::getUaName)).collect(Collectors.toList());
                break;
            case "difficulty":
                tests = tests.stream()
                        .sorted(Comparator.comparing(Test::getDifficulty)).collect(Collectors.toList());
                break;
            case "questionAmount":
                tests = tests.stream()
                        .sorted(Comparator.comparing(Test::getQuestionAmount)).collect(Collectors.toList());
                break;
            default:
                tests = tests.stream()
                        .sorted(Comparator.comparing(Test::getId)).collect(Collectors.toList());
                break;
        }
        return tests;
    }
}