/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans.user;

import com.kvlahov.common.Mode;
import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.ShippingInfoes;
import com.kvlahov.models.Users;
import com.kvlahov.common.Utilities;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "shippingInfo")
@ViewScoped
public class ShippingInfoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private IUnitOfWork unitOfWork;
    private List<ShippingInfoes> shippingInfoes;
    private ShippingInfoes selectedShippingInfo;
    private Mode mode;

    @PostConstruct
    private void init() {
        unitOfWork = new AppUnitOfWork();

        shippingInfoes = getUsersShippingInfoes();
    }

    public List<ShippingInfoes> getUsersShippingInfoes() {
        Users user = Utilities.getLoggedInUser().get();
        return unitOfWork.getShippingInfoesRepository().getShippingInfoesForUser(user);
    }

    public void save(ShippingInfoes entity) {
        switch (mode) {
            case CREATE:
                addShippingInfo(entity);
                break;
            case EDIT:
                editShippingInfo(entity);
                break;
            case DELETE:
                deleteShippingInfo(entity);
                break;
        }

        shippingInfoes = getUsersShippingInfoes();
        Utilities.getLoggedInUser().get().setShippingInfoesList(shippingInfoes);
        selectedShippingInfo = null;
    }

    private void addShippingInfo(ShippingInfoes entity) {
        entity.setUser(Utilities.getLoggedInUser().get());
        unitOfWork.getShippingInfoesRepository().create(entity);
        unitOfWork.saveChanges();
    }

    private void editShippingInfo(ShippingInfoes entity) {
        unitOfWork.getShippingInfoesRepository().update(entity);
        unitOfWork.saveChanges();
    }

    private void deleteShippingInfo(ShippingInfoes entity) {
        unitOfWork.getShippingInfoesRepository().delete(entity);
        unitOfWork.saveChanges();
    }

    public ShippingInfoes getSelectedShippingInfo() {
        return selectedShippingInfo;
    }

    public Mode getMode() {
        return mode;
    }

    public void setSelectedShippingInfo(ShippingInfoes selectedShippingInfo, Mode mode) {
        this.selectedShippingInfo = selectedShippingInfo;
        this.mode = mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setCreateMode() {
        this.mode = Mode.CREATE;
        this.selectedShippingInfo = new ShippingInfoes();
    }
}
