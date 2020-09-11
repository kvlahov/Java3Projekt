/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans;

import com.kvlahov.dal.IRolesRepository;
import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.IUsersRepository;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.Roles;
import com.kvlahov.models.Users;
import com.kvlahov.common.Utilities;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author evlakre
 */

@ManagedBean(name = "app", eager = true)
@ApplicationScoped
public class AppBean {

    @PostConstruct
    public void init() {
        initData();
    }

    private void initData() {
        IUnitOfWork unitOfWork = new AppUnitOfWork();
        
        IUsersRepository usersRepo = unitOfWork.getUsersRepository();
        if(!usersRepo.getByUsername("user").isPresent()){
            usersRepo.create(new Users("user", "passWORD"));
        }
        if(!usersRepo.getByUsername("admin").isPresent()){
            usersRepo.create(new Users("admin", "passWORD"));
        }
        
        IRolesRepository rolesRepo = unitOfWork.getRolesRepository();
        if(!rolesRepo.userInRole("user", "user")){
            rolesRepo.create(new Roles("user", "user"));
        }
        if(!rolesRepo.userInRole("admin", "admin")){
            rolesRepo.create(new Roles("admin", "admin"));
        }
        
        unitOfWork.saveChanges();
    }
    
    public String getFormattedDateTime(Date date){
        return Utilities.getFormattedDateTime(date);
    }
}
