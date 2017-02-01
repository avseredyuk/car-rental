package com.avseredyuk.carrental.service;

import com.avseredyuk.carrental.domain.BasicEntity;

import java.util.List;

/**
 * Created by lenfer on 1/8/17.
 */
public interface CrudService<T extends BasicEntity> {
    boolean persist(T object);
    T read(int key);
    boolean update(T object);
    boolean delete(T object);
    List<T> findAll();
    List<T> findAll(int startIndex, int size);
    int getCount();
}
