/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.models;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author evlakre
 */
@Entity
@Table(name = "receipt_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReceiptItem.findAll", query = "SELECT r FROM ReceiptItem r")
    , @NamedQuery(name = "ReceiptItem.findByReceiptItemId", query = "SELECT r FROM ReceiptItem r WHERE r.receiptItemId = :receiptItemId")
    , @NamedQuery(name = "ReceiptItem.findByItemTitle", query = "SELECT r FROM ReceiptItem r WHERE r.itemTitle = :itemTitle")
    , @NamedQuery(name = "ReceiptItem.findByItemDescription", query = "SELECT r FROM ReceiptItem r WHERE r.itemDescription = :itemDescription")
    , @NamedQuery(name = "ReceiptItem.findByPricePerUnit", query = "SELECT r FROM ReceiptItem r WHERE r.pricePerUnit = :pricePerUnit")
    , @NamedQuery(name = "ReceiptItem.findByQuantity", query = "SELECT r FROM ReceiptItem r WHERE r.quantity = :quantity")
    , @NamedQuery(name = "ReceiptItem.findByTotal", query = "SELECT r FROM ReceiptItem r WHERE r.total = :total")})
public class ReceiptItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "receipt_item_id")
    private Long receiptItemId;
    @Basic(optional = false)
    @Column(name = "item_title")
    private String itemTitle;
    @Column(name = "item_description")
    private String itemDescription;
    @Column(name = "price_per_unit")
    private Float pricePerUnit;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total")
    private Float total;
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    @ManyToOne
    private Currencies currency;
    @JoinColumn(name = "receipt_id", referencedColumnName = "receipt_id")
    @ManyToOne
    private Receipt receipt;

    public ReceiptItem() {
    }

    public ReceiptItem(Long receiptItemId, String itemTitle) {
        this.receiptItemId = receiptItemId;
        this.itemTitle = itemTitle;
    }

    public ReceiptItem(ItemViewModel vm){
        this.itemTitle = vm.getTitle();
        this.itemDescription = vm.getDescription();
        this.pricePerUnit = vm.getPrice();
        this.quantity = vm.getQuantity();
        this.total = vm.getTotal();
        this.currency = vm.getCurrency();
    }
    
    public Long getReceiptItemId() {
        return receiptItemId;
    }

    public void setReceiptItemId(Long receiptItemId) {
        this.receiptItemId = receiptItemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Float getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptItemId != null ? receiptItemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReceiptItem)) {
            return false;
        }
        ReceiptItem other = (ReceiptItem) object;
        if ((this.receiptItemId == null && other.receiptItemId != null) || (this.receiptItemId != null && !this.receiptItemId.equals(other.receiptItemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kvlahov.models.ReceiptItem[ receiptItemId=" + receiptItemId + " ]";
    }
    
}
