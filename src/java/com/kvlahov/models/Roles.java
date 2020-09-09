/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.models;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author evlakre
 */
@Entity
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByUsername", query = "SELECT r FROM Roles r WHERE r.rolesPK.username = :username")
    , @NamedQuery(name = "Roles.findByRolename", query = "SELECT r FROM Roles r WHERE r.rolesPK.rolename = :rolename")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolesPK rolesPK;

    public Roles() {
    }

    public Roles(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    public Roles(String username, String rolename) {
        this.rolesPK = new RolesPK(username, rolename);
    }

    public RolesPK getRolesPK() {
        return rolesPK;
    }

    public void setRolesPK(RolesPK rolesPK) {
        this.rolesPK = rolesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolesPK != null ? rolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.rolesPK == null && other.rolesPK != null) || (this.rolesPK != null && !this.rolesPK.equals(other.rolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kvlahov.models.Roles[ rolesPK=" + rolesPK + " ]";
    }
    
}
