package model.service;

import model.dao.DaoFactory;
import model.dao.TestDao;
import model.entity.Test;


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

}