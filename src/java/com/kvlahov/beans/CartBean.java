/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans;

import com.kvlahov.models.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "cart", eager = true)
@SessionScoped
public class CartBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Item> items;
    private boolean isCartEmpty;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isIsCartEmpty() {
        return items.isEmpty();
    }

    public void addToCart(Item item) {
        Optional<Item> foundItem = items.stream()
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
    
    public double getCartTotal(){
        return items.stream()
                .mapToDouble(i -> i.getTotal())
                .sum();
    }

}
