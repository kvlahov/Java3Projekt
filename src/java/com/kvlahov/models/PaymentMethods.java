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
@Table(name = "payment_methods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentMethods.findAll", query = "SELECT p FROM PaymentMethods p")
    , @NamedQuery(name = "PaymentMethods.findByPaymentMethodId", query = "SELECT p FROM PaymentMethods p WHERE p.paymentMethodId = :paymentMethodId")
    , @NamedQuery(name = "PaymentMethods.findByName", query = "SELECT p FROM PaymentMethods p WHERE p.name = :name")})
public class PaymentMethods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "payment_method_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer paymentMethodId;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "paymentMethod")
    private List<Receipt> receiptList;

    public PaymentMethods() {
    }

    public PaymentMethods(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (paymentMethodId != null ? paymentMethodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethods)) {
            return false;
        }
        PaymentMethods other = (PaymentMethods) object;
        if ((this.paymentMethodId == null && other.paymentMethodId != null) || (this.paymentMethodId != null && !this.paymentMethodId.equals(other.paymentMethodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kvlahov.models.PaymentMethods[ paymentMethodId=" + paymentMethodId + " ]";
    }
    
}
