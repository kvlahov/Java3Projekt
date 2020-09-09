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
@Table(name = "currencies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Currencies.findAll", query = "SELECT c FROM Currencies c")
    , @NamedQuery(name = "Currencies.findByCurrencyId", query = "SELECT c FROM Currencies c WHERE c.currencyId = :currencyId")
    , @NamedQuery(name = "Currencies.findByName", query = "SELECT c FROM Currencies c WHERE c.name = :name")})
public class Currencies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private Integer currencyId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "currencyId")
    private List<ReceiptItem> receiptItemList;
    @OneToMany(mappedBy = "currencyId")
    private List<Items> itemsList;

    public Currencies() {
    }

    public Currencies(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<ReceiptItem> getReceiptItemList() {
        return receiptItemList;
    }

    public void setReceiptItemList(List<ReceiptItem> receiptItemList) {
        this.receiptItemList = receiptItemList;
    }

    @XmlTransient
    public List<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(List<Items> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (currencyId != null ? currencyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Currencies)) {
            return false;
        }
        Currencies other = (Currencies) object;
        if ((this.currencyId == null && other.currencyId != null) || (this.currencyId != null && !this.currencyId.equals(other.currencyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kvlahov.models.Currencies[ currencyId=" + currencyId + " ]";
    }
    
}
