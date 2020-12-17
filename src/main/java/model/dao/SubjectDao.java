package model.dao;

import model.entity.Subject;

import java.util.List;

public interface SubjectDao {

    public Subject findById(int id);
    public List<Subject> findAll();
}
