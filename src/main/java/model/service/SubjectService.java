package model.service;

import model.dao.DaoFactory;
import model.dao.SubjectDao;

import model.entity.Subject;

import java.util.List;

public class SubjectService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Subject> findAll() {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findAll();
    }

    public Subject findById(int id) {
        SubjectDao dao = daoFactory.createSubjectDao();
        return dao.findById(id);
    }
}