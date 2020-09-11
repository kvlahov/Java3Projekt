/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans.user;

import com.kvlahov.beans.CartBean;
import com.kvlahov.common.Utilities;
import com.kvlahov.dal.IReceiptItemRepository;
import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.PaymentMethods;
import com.kvlahov.models.Receipt;
import com.kvlahov.models.ReceiptItem;
import com.kvlahov.models.ShippingInfoes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "checkout")
@RequestScoped
public class CheckoutBean {

    private IUnitOfWork unitOfWork;
    private ShippingInfoes selectedShippingInfo;

    @ManagedProperty(value = "#{cart}")
    private CartBean cart;

    @PostConstruct
    public void init() {
        unitOfWork = new AppUnitOfWork();
        List<ShippingInfoes> shippinginfoes = Utilities.getLoggedInUser().get().getShippingInfoesList();
        selectedShippingInfo = shippinginfoes != null && !shippinginfoes.isEmpty() ? shippinginfoes.get(0) : null;
    }

    public ShippingInfoes getSelectedShippingInfo() {
        return selectedShippingInfo;
    }

    public void setSelectedShippingInfo(ShippingInfoes selectedShippingInfo) {
        this.selectedShippingInfo = selectedShippingInfo;
    }
    
    public String purchaseWithCard() {
        PaymentMethods pm = unitOfWork.getPaymentMethodsRepository().getById(1).get();
        saveOrder(pm, selectedShippingInfo);
        return "/secured/user/orderSuccess";
    }

    public String purchaseWithPaypal() {
        PaymentMethods pm = unitOfWork.getPaymentMethodsRepository().getById(2).get();
        saveOrder(pm, selectedShippingInfo);
        return "/secured/user/orderSuccess";
    }

    private void saveOrder(PaymentMethods pm, ShippingInfoes si) {
        Receipt receipt = new Receipt(new Date(), cart.getCartTotal(), pm, si, Utilities.getLoggedInUser().get());
        IReceiptItemRepository repo = unitOfWork.getReceiptItemRepository();
        List<ReceiptItem> receiptItems = cart.getItems().stream()
                .map(ReceiptItem::new)
                .collect(Collectors.toList());
        receiptItems.forEach(i -> {
            i.setReceipt(receipt);
//            repo.create(i);
        });

        receipt.setReceiptItemList(receiptItems);
        unitOfWork.getReceiptRepository().create(receipt);
        
        unitOfWork.saveChanges();
        cart.clearCart();
    }

    public CartBean getCart() {
        return cart;
    }

    public void setCart(CartBean cart) {
        this.cart = cart;
    }
    
    
    
}
