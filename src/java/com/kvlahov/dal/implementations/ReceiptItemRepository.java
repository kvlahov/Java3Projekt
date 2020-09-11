/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IReceiptItemRepository;
import com.kvlahov.models.Receipt;
import com.kvlahov.models.ReceiptItem;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public class ReceiptItemRepository extends GenericRepository<ReceiptItem, Long> implements IReceiptItemRepository{

    public ReceiptItemRepository(EntityManager entityManager) {
        super(entityManager, ReceiptItem.class);
    }

    @Override
    public List<ReceiptItem> getByReceipt(Receipt receipt) {
        return entityManager.createQuery("SELECT r FROM ReceiptItem r WHERE r.receipt.receiptId = :receiptId", ReceiptItem.class)
                .setParameter("receiptId", receipt.getReceiptId())
                .getResultList();
    }
    
}
