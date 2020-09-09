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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "items", eager = true)
@ViewScoped
public class ItemsBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private List<Item> items;
    private Item previewItem;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
        items.add(new Item(1,"Samsung galaxy A21s", "Smartphone, 6,5\", Octa-Core 2Ghz, 3GB", 1599, "images/samsung_ga21.jpg"));
        items.add(new Item(2,"Huawei P40 Lite", "Smartphone 6.39\",Octa-Core 2.2Ghz, 4GB", 1349, "images/huawei_p40.jpg"));
        items.add(new Item(3,"Huawei Y5P Green", "Smartphone, 5,45\"", 749, "images/huawei_y5p.jpg"));
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getPreviewItem() {
        return previewItem;
    }

    public void setPreviewItem(Item previewItem) {
        this.previewItem = previewItem;
    }
    
    public void addToCart(String string){
        String s = string;
    }
}
