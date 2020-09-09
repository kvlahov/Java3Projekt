/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans.admin;

import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.LoginInfoes;
import com.kvlahov.utilities.Utilities;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author evlakre
 */

@ManagedBean(name = "userInfo")
@ViewScoped
public class UserInfoBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private IUnitOfWork unitOfWork;
    private List<LoginInfoes> loginInfoes;
    
    @PostConstruct
    public void init(){
        unitOfWork = new AppUnitOfWork();
        loginInfoes = unitOfWork.getLoginInfoesRepository().getAll();
        loginInfoes.sort(Comparator.comparing(LoginInfoes::getDatetime).reversed());
    }

    public List<LoginInfoes> getLoginInfoes() {
        return loginInfoes;
    }
}
