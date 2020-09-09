/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IItemsRepository;
import com.kvlahov.dal.IReceiptRepository;
import com.kvlahov.dal.IRepository;
import com.kvlahov.dal.IRolesRepository;
import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.IUsersRepository;
import com.kvlahov.models.Categories;
import com.kvlahov.models.Currencies;
import com.kvlahov.models.Items;
import com.kvlahov.models.LoginInfoes;
import com.kvlahov.models.PaymentMethods;
import com.kvlahov.models.ReceiptItem;
import com.kvlahov.models.ShippingInfoes;
import com.kvlahov.utilities.Utilities;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public class AppUnitOfWork implements IUnitOfWork {
    private final EntityManager entityManager;

    private final IReceiptRepository receiptRepository;
    private final IUsersRepository usersRepository;
    private final IRepository<Categories, Integer> categoriesRepository;
    private final IRepository<Currencies, Integer> currenciesRepository;
    private final IRepository<PaymentMethods, Integer> paymentMethodsRepository;
    private final IItemsRepository itemsRepository;
    private final IRepository<LoginInfoes, Long> loginInfoesRepository;
    private final IRepository<ReceiptItem, Long> receiptItemRepository;
    private final IRepository<ShippingInfoes, Long> shippingInfoesRepository;
    private IRolesRepository rolesRepository;

    public AppUnitOfWork() {
        this(Utilities.getEntityManager());
    }
    
    public AppUnitOfWork(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.receiptRepository = new ReceiptRepository(entityManager);
        this.usersRepository = new UsersRepository(entityManager);
        this.categoriesRepository = new GenericRepository<>(entityManager, Categories.class);
        this.currenciesRepository = new GenericRepository<>(entityManager, Currencies.class);
        this.paymentMethodsRepository = new GenericRepository<>(entityManager, PaymentMethods.class);
        this.itemsRepository = new ItemsRepository(entityManager);
        this.loginInfoesRepository = new GenericRepository<>(entityManager, LoginInfoes.class);
        this.receiptItemRepository = new GenericRepository<>(entityManager, ReceiptItem.class);
        this.shippingInfoesRepository = new GenericRepository<>(entityManager, ShippingInfoes.class);
        this.rolesRepository = new RolesRepository(entityManager);
    }

    @Override
    public void saveChanges() {
        try {
            entityManager.getTransaction().begin();
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public IReceiptRepository getReceiptRepository() {
        return receiptRepository;
    }

    @Override
    public IUsersRepository getUsersRepository() {
        return usersRepository;
    }

    @Override
    public IRepository<Categories, Integer> getCategoriesRepository() {
        return categoriesRepository;
    }

    @Override
    public IRepository<Currencies, Integer> getCurrenciesRepository() {
        return currenciesRepository;
    }

    @Override
    public IRepository<PaymentMethods, Integer> getPaymentMethodsRepository() {
        return paymentMethodsRepository;
    }

    @Override
    public IItemsRepository getItemsRepository() {
        return itemsRepository;
    }

    @Override
    public IRepository<LoginInfoes, Long> getLoginInfoesRepository() {
        return loginInfoesRepository;
    }

    @Override
    public IRepository<ReceiptItem, Long> getReceiptItemRepository() {
        return receiptItemRepository;
    }

    @Override
    public IRepository<ShippingInfoes, Long> getShippingInfoesRepository() {
        return shippingInfoesRepository;
    }    

    @Override
    public IRolesRepository getRolesRepository() {
        return rolesRepository;
    }
}
