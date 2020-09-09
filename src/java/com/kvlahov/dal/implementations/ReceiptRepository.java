/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IReceiptRepository;
import com.kvlahov.models.Receipt;
import com.kvlahov.models.Users;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public class ReceiptRepository extends GenericRepository<Receipt, Long> implements IReceiptRepository {

    public ReceiptRepository(EntityManager entityManager) {
        super(entityManager, Receipt.class);
    }

    @Override
    public List<Receipt> getReceiptsForUser(Users user) {
        return entityManager.createQuery("SELECT r FROM Receipt r WHERE r.user.userId = :userId", Receipt.class)
                .setParameter("userId", user.getUserId())
                .getResultList();
    }
    
}
