/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author evlakre
 */
@Entity
@Table(name = "login_infoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginInfoes.findAll", query = "SELECT l FROM LoginInfoes l")
    , @NamedQuery(name = "LoginInfoes.findByLoginInfoId", query = "SELECT l FROM LoginInfoes l WHERE l.loginInfoId = :loginInfoId")
    , @NamedQuery(name = "LoginInfoes.findByIpAddress", query = "SELECT l FROM LoginInfoes l WHERE l.ipAddress = :ipAddress")
    , @NamedQuery(name = "LoginInfoes.findByDatetime", query = "SELECT l FROM LoginInfoes l WHERE l.datetime = :datetime")})
public class LoginInfoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "login_info_id")
    private Long loginInfoId;
    @Basic(optional = false)
    @Column(name = "ip_address")
    private String ipAddress;
    @Basic(optional = false)
    @Column(name = "datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users user;

    public LoginInfoes() {
    }

    public LoginInfoes(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public LoginInfoes(String ipAddress, Date datetime, Users user) {
        this.ipAddress = ipAddress;
        this.datetime = datetime;
        this.user = user;
    }

    public Long getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginInfoId != null ? loginInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginInfoes)) {
            return false;
        }
        LoginInfoes other = (LoginInfoes) object;
        if ((this.loginInfoId == null && other.loginInfoId != null) || (this.loginInfoId != null && !this.loginInfoId.equals(other.loginInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kvlahov.models.LoginInfoes[ loginInfoId=" + loginInfoId + " ]";
    }
    
}
