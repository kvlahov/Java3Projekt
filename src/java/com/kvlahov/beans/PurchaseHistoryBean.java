/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans;

import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.Receipt;
import com.kvlahov.models.Users;
import com.kvlahov.common.Utilities;
import com.kvlahov.models.ReceiptItem;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "purchaseHistory")
@ViewScoped
public class PurchaseHistoryBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Receipt> receipts;
    private IUnitOfWork unitOfWork;
    private Receipt selectedReceipt;

    @PostConstruct
    public void init() {
        unitOfWork = new AppUnitOfWork();

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        if (request.isUserInRole("admin")) {
            receipts = unitOfWork.getReceiptRepository().getAll();
        } else if (request.isUserInRole("user")) {
            Optional<Users> users = Utilities.getLoggedInUser();
            if (users.isPresent()) {
                receipts = unitOfWork.getReceiptRepository().getReceiptsForUser(users.get());
            }
        }
    }

    public Receipt getSelectedReceipt() {
        return selectedReceipt;
    }

    public void setSelectedReceipt(Receipt selectedReceipt) {
        if(selectedReceipt.getReceiptItemList().isEmpty()){
            List<ReceiptItem> items  = unitOfWork.getReceiptItemRepository().getByReceipt(selectedReceipt);
            selectedReceipt.setReceiptItemList(items);
        }
        this.selectedReceipt = selectedReceipt;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

}
