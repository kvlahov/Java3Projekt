/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans;

import com.kvlahov.beans.user.ShippingInfoBean;
import com.kvlahov.common.Utilities;
import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.ItemViewModel;
import com.kvlahov.models.Items;
import com.kvlahov.models.PaymentMethods;
import com.kvlahov.models.Receipt;
import com.kvlahov.models.ReceiptItem;
import com.kvlahov.models.ShippingInfoes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "cart", eager = true)
@SessionScoped
public class CartBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemViewModel> items;
    private boolean isCartEmpty;
    
    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public List<ItemViewModel> getItems() {
        return items;
    }

    public boolean isIsCartEmpty() {
        return items.isEmpty();
    }

    public void addToCart(ItemViewModel item) {
        Optional<ItemViewModel> foundItem = items.stream()
                .filter(i -> i.getId() == item.getId())
                .findFirst();
        if (foundItem.isPresent()) {
            foundItem.get().addToQuantity(item.getQuantity());
        } else {
            items.add(item);
        }
    }

    public void removeFromCart(int itemId) {
        items.removeIf(i -> i.getId() == itemId);
    }

    public Float getCartTotal() {
        return (float) items.stream()
                .mapToDouble(i -> i.getTotal())
                .sum();
    }    
    
    public void clearCart() {
        items.clear();
    }

}
