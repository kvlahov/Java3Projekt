/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans;

import com.kvlahov.dal.IRepository;
import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.dal.implementations.GenericRepository;
import com.kvlahov.dal.implementations.UsersRepository;
import com.kvlahov.models.LoginInfoes;
import com.kvlahov.models.Users;
import com.kvlahov.common.Utilities;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "login")
@ViewScoped
public class LoginBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String message;

    private IUnitOfWork unitOfWork;

    @PostConstruct
    public void init() {
        unitOfWork = new AppUnitOfWork();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void login() {
        if (!unitOfWork.getUsersRepository().userExists(username, password)) {
            message = "Invalid username or password";
            return;
        }

        try {
            message = "User Exists. Logging in...";
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            Users user = unitOfWork.getUsersRepository().getByUsername(username).get();
            externalContext.getSessionMap().put("user", user);
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

            request.login(username, password);
            setUserLoginTime(user);
            logUserLogin(request, user);
            redirect(externalContext, request);
            
            unitOfWork.saveChanges();
            
        } catch (IOException | ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();
        try {
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            request.logout();
            redirectToHome(externalContext);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void redirectToHome(ExternalContext externalContext) throws IOException {
        externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
    }

    private void redirect(ExternalContext externalContext, HttpServletRequest request) throws IOException {
        String referedPage = externalContext.getRequestHeaderMap().get("referer");
        if (request.getRequestURL().toString().equals(referedPage)) {
            redirectToHome(externalContext);
        } else {
            externalContext.redirect(referedPage);
        }
    }

    private void setUserLoginTime(Users user) {
        user.setLastlogin(new Date());
        unitOfWork.getUsersRepository().update(user);
    }

    private void logUserLogin(HttpServletRequest request, Users user) {
        LoginInfoes model = new LoginInfoes(Utilities.getClientIpAddress(request), new Date(), user);
        unitOfWork.getLoginInfoesRepository().create(model);
    }
}
