/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.beans;

import com.kvlahov.dal.IUnitOfWork;
import com.kvlahov.dal.IUsersRepository;
import com.kvlahov.dal.implementations.AppUnitOfWork;
import com.kvlahov.models.Roles;
import com.kvlahov.models.Users;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author evlakre
 */
@ManagedBean(name = "register")
@ViewScoped
public class RegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String usernameValidationMsg;
    private String passwordValidationMsg;
    private IUnitOfWork unitOfWork;
    private String pass;
    private String repeatPass;
    private String username;

    @ManagedProperty(value = "#{login}")
    private LoginBean login;

    @PostConstruct
    public void init() {
        unitOfWork = new AppUnitOfWork();
    }

    public String getUsernameValidationMsg() {
        return usernameValidationMsg;
    }

    public void setUsernameValidationMsg(String usernameValidationMsg) {
        this.usernameValidationMsg = usernameValidationMsg;
    }

    public LoginBean getLogin() {
        return login;
    }

    public void setLogin(LoginBean login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRepeatPass() {
        return repeatPass;
    }

    public void setRepeatPass(String repeatPass) {
        this.repeatPass = repeatPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordValidationMsg() {
        return passwordValidationMsg;
    }

    public void setPasswordValidationMsg(String passwordValidationMsg) {
        this.passwordValidationMsg = passwordValidationMsg;
    }

    public void registerUser() {
        IUsersRepository repo = unitOfWork.getUsersRepository();

        boolean userValid = validateUser(repo);

        if (!userValid) {
            return;
        }

        repo.create(new Users(username, pass));
        unitOfWork.getRolesRepository().create(new Roles(username, "user"));
        unitOfWork.saveChanges();

        login.setUsername(username);
        login.setPassword(pass);
        login.login();
    }

    private boolean validateUser(IUsersRepository repo) {
        boolean userValid = true;
        if (repo.getByUsername(username).isPresent()) {
            usernameValidationMsg = "User with this username already exists. Please change username";
            userValid = false;
        }
        else {
            usernameValidationMsg = "";
        }

        if (!pass.equals(repeatPass)) {
            passwordValidationMsg = "Password and repeat password do not match";
            userValid = false;
        } else {
            usernameValidationMsg = "";
        }

        return userValid;
    }

}
