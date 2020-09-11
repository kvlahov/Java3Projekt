/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IShippingInfoesRepository;
import com.kvlahov.models.ShippingInfoes;
import com.kvlahov.models.Users;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public class ShippingInfoesRepository extends GenericRepository<ShippingInfoes, Long> implements IShippingInfoesRepository{

    public ShippingInfoesRepository(EntityManager entityManager) {
        super(entityManager, ShippingInfoes.class);
    }

    @Override
    public List<ShippingInfoes> getShippingInfoesForUser(Users user) {
        return entityManager.createQuery("SELECT s FROM ShippingInfoes s WHERE s.user.userId = :userId", ShippingInfoes.class)
                .setParameter("userId", user.getUserId())
                .getResultList();
    }
    
}
