/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

public class GenericRepository<T, ID extends Serializable> implements IRepository<T, ID>{
    final EntityManager entityManager;
    private final Class<T> type;

    public GenericRepository(EntityManager entityManager, Class<T> type) {
        this.entityManager = entityManager;
        this.type = type;
    }
    
    @Override
    public boolean create(T entity) {
        try {
            entityManager.persist(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(T entity) {
        try {
            entityManager.merge(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<T> getAll() {
        return entityManager.createQuery("SELECT e FROM " + type.getName() + " e", type).getResultList();
    }

    @Override
    public Optional<T> getById(ID id) {
        T entity = entityManager.find(type, id);
        return Optional.of(entity);
    }

    @Override
    public boolean delete(T entity) {
        try {
            entityManager.remove(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
}
