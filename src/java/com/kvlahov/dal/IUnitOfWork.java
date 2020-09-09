/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import com.kvlahov.models.*;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public interface IUnitOfWork {
    void saveChanges();
    IReceiptRepository getReceiptRepository();
    IUsersRepository getUsersRepository();
    IRepository<Categories, Integer> getCategoriesRepository();
    IRepository<Currencies, Integer> getCurrenciesRepository();
    IRepository<PaymentMethods, Integer> getPaymentMethodsRepository();
    IItemsRepository getItemsRepository();
    IRepository<LoginInfoes, Long> getLoginInfoesRepository();
    IRepository<ReceiptItem, Long> getReceiptItemRepository();
    IRepository<ShippingInfoes, Long> getShippingInfoesRepository();   
    IRolesRepository getRolesRepository();   
}
