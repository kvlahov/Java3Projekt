/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.models;

/**
 *
 * @author evlakre
 */
public class Item {
    private int id;
    private String title;
    private String description;
    private double price;
    private String imgPath;
    private int quantity = 1;
    private double total;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
    
    public double getTotal(){
        return price * quantity;
    }
    
      public void addToQuantity(int quantity) {
        this.quantity += quantity;
    }
        
    public Item() {
    }
    
    public Item(int id, String title, String description, double price, String imgPath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imgPath = imgPath;
    }

  
    
}
