/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IItemsRepository;
import com.kvlahov.models.Items;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public class ItemsRepository extends GenericRepository<Items, Long> implements IItemsRepository {
    
    public ItemsRepository(EntityManager entityManager) {
        super(entityManager, Items.class);
    }
    
}
