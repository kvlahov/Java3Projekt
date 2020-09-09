/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author evlakre
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPasswordhash", query = "SELECT u FROM Users u WHERE u.passwordhash = :passwordhash")
    , @NamedQuery(name = "Users.findByDatecreated", query = "SELECT u FROM Users u WHERE u.datecreated = :datecreated")
    , @NamedQuery(name = "Users.findByLastlogin", query = "SELECT u FROM Users u WHERE u.lastlogin = :lastlogin")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "passwordhash")
    private String passwordhash;
    @Basic(optional = false)
    @Column(name = "datecreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreated;
    @Column(name = "lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastlogin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<ShippingInfoes> shippingInfoesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<LoginInfoes> loginInfoesList;
    @OneToMany(mappedBy = "user")
    private List<Receipt> receiptList;

    public Users() {
    }

    public Users(String username, String passwordhash) {
        this.username = username;
        this.passwordhash = passwordhash;
        this.datecreated = new Date();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    @XmlTransient
    public List<ShippingInfoes> getShippingInfoesList() {
        return shippingInfoesList;
    }

    public void setShippingInfoesList(List<ShippingInfoes> shippingInfoesList) {
        this.shippingInfoesList = shippingInfoesList;
    }

    @XmlTransient
    public List<LoginInfoes> getLoginInfoesList() {
        return loginInfoesList;
    }

    public void setLoginInfoesList(List<LoginInfoes> loginInfoesList) {
        this.loginInfoesList = loginInfoesList;
    }

    @XmlTransient
    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        return !((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId)));
    }

    @Override
    public String toString() {
        return "com.kvlahov.models.Users_1[ userId=" + userId + " ]";
    }
    
}
