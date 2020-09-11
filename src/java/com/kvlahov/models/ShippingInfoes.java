/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.models;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author evlakre
 */
@Entity
@Table(name = "shipping_infoes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ShippingInfoes.findAll", query = "SELECT s FROM ShippingInfoes s")
    , @NamedQuery(name = "ShippingInfoes.findByShippingInfoId", query = "SELECT s FROM ShippingInfoes s WHERE s.shippingInfoId = :shippingInfoId")
    , @NamedQuery(name = "ShippingInfoes.findByAddress", query = "SELECT s FROM ShippingInfoes s WHERE s.address = :address")
    , @NamedQuery(name = "ShippingInfoes.findByCity", query = "SELECT s FROM ShippingInfoes s WHERE s.city = :city")
    , @NamedQuery(name = "ShippingInfoes.findByPostalcode", query = "SELECT s FROM ShippingInfoes s WHERE s.postalcode = :postalcode")
    , @NamedQuery(name = "ShippingInfoes.findByAdditionalinfo", query = "SELECT s FROM ShippingInfoes s WHERE s.additionalinfo = :additionalinfo")})
public class ShippingInfoes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "shipping_info_id")
    private Long shippingInfoId;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @Column(name = "postalcode")
    private String postalcode;
    @Column(name = "additionalinfo")
    private String additionalinfo;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users user;
    @OneToMany(mappedBy = "shippingInfo")
    private List<Receipt> receiptList;

    public ShippingInfoes() {
    }

    public ShippingInfoes(Long shippingInfoId) {
        this.shippingInfoId = shippingInfoId;
    }

    public ShippingInfoes(Long shippingInfoId, String address, String city, String postalcode) {
        this.shippingInfoId = shippingInfoId;
        this.address = address;
        this.city = city;
        this.postalcode = postalcode;
    }

    public Long getShippingInfoId() {
        return shippingInfoId;
    }

    public void setShippingInfoId(Long shippingInfoId) {
        this.shippingInfoId = shippingInfoId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @XmlTransient
    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }
    
    public String getUserFriendlyAddress(){
        return String.format("%s, %s %s", address, postalcode, city);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shippingInfoId != null ? shippingInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShippingInfoes)) {
            return false;
        }
        ShippingInfoes other = (ShippingInfoes) object;
        if ((this.shippingInfoId == null && other.shippingInfoId != null) || (this.shippingInfoId != null && !this.shippingInfoId.equals(other.shippingInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kvlahov.models.ShippingInfoes[ shippingInfoId=" + shippingInfoId + " ]";
    }
    
}
