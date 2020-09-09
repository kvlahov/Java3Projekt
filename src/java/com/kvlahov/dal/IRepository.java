/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author evlakre
 */
public interface IRepository<T, ID extends Serializable> {
    boolean create(T entity);
    boolean update(T entity);
    List<T> getAll();
    Optional<T> getById(ID id);
    boolean delete(T entity);
}
