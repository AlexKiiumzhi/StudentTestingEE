package model.dao;

import model.entity.Test;

import java.util.List;

public interface TestDao {

    public List<Test> findAllPagination(int startIndex, int recordPerPage);
    public int numberOfRows() ;
}
