package com.clevertec.strezhik.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    void save(T t);

    T selectById(int id);

    void update(T t);

    void delete(int id);

    List<T> findPortion(int start, int total) throws SQLException;
}
