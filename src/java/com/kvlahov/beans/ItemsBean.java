/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans;

import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.Categories;
import com.kvlahov.models.ItemViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.hibernate.FetchMode;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "items", eager = true)
@ViewScoped
public class ItemsBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ItemViewModel> items;
    private List<ItemViewModel> filteredItems;
    private ItemViewModel previewItem;
    private IUnitOfWork unitOfWork;
    private List<Categories> categories;
    private int currentPage = 1;
    private int maxPages;
    private int showPerPage = 15;
    
    private List<String> selectedCategoriesIds;
    private String titleSearchTerm;

    @PostConstruct
    public void init() {
        unitOfWork = new AppUnitOfWork();

        items = unitOfWork.getItemsRepository().getAll()
                .stream()
                .map(ItemViewModel::new)
                .collect(Collectors.toList());
        
        filteredItems = items;
        categories = unitOfWork.getCategoriesRepository().getAll();
    }

    public List<ItemViewModel> getItems() {
        return items;
    }

    public ItemViewModel getPreviewItem() {
        return previewItem;
    }

    public void setPreviewItem(ItemViewModel previewItem) {
        this.previewItem = previewItem;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<String> getSelectedCategoriesIds() {
        return selectedCategoriesIds;
    }

    public void setSelectedCategoriesIds(List<String> selectedCategories) {
        this.selectedCategoriesIds = selectedCategories;
    }

    public String getTitleSearchTerm() {
        return titleSearchTerm;
    }

    public void setTitleSearchTerm(String titleSearchTerm) {
        this.titleSearchTerm = titleSearchTerm;
    }

    public List<ItemViewModel> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<ItemViewModel> filteredItems) {
        this.filteredItems = filteredItems;
    }
    
    public void filterItems(){
        filteredItems = items.stream()
                .filter(i -> {
                    if(selectedCategoriesIds.isEmpty()){
                        return true;
                    } else {
                        return selectedCategoriesIds.contains(i.getCategory().getCategoryId().toString());
                    }
                })
                .filter(i -> {
                    if(titleSearchTerm == null || titleSearchTerm.trim().isEmpty()){
                        return true;
                    } else {
                        return i.getTitle().toLowerCase().contains(titleSearchTerm.toLowerCase());
                    }
                })
                .collect(Collectors.toList());
    }

    public String getClassForCategory(Categories category) {
        switch (category.getTitle()) {
            case "Smartphones":
                return "fas fa-mobile";
            case "Laptops":
                return "fas fa-laptop-code";
            case "TV":
                return "fas fa-tv";
            default:
                return "";
        }
    }

}
