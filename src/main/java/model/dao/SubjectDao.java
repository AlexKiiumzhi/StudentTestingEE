package model.dao;

import model.entity.Subject;

import java.util.List;

public interface SubjectDao {

    Subject findById(int id);
    List<Subject> findAll();
}
