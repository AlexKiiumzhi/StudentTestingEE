package model.dao;

import model.entity.Test;

import java.util.List;

public interface TestDao {

    List<Test> findAllPagination(int startIndex, int recordPerPage);
    int numberOfRows() ;
    List<Test> findBySubject(Long subjectId);
    void createTest (Test test, Long subjectId);
    void editTest (Test test);
    void deleteTest (Long testId);
    /*public List<Test> findAllTestsByName();*/
}
