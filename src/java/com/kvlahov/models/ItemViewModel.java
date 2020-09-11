/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.models;

import java.io.Serializable;

/**
 *
 * @author evlakre
 */
public class ItemViewModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private Float price;
    private String imgPath;
    private int quantity = 1;
    private Currencies currency;
    private Categories category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return price * quantity;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public void addToQuantity(int quantity) {
        this.quantity += quantity;
    }

    public ItemViewModel() {
    }

    public ItemViewModel(Long id, String title, String description, Float price, String imgPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imgPath = imgPath;
    }

    public ItemViewModel(Items model) {
        this.id = model.getItemId();
        this.title = model.getTitle();
        this.description = model.getDescription();
        this.price = model.getPricePerUnit();
        this.imgPath = model.getImgPath();
        this.currency = model.getCurrency();
        this.category = model.getCategory();
    }

    public Currencies getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }
}
