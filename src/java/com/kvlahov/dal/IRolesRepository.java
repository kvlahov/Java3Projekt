/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import com.kvlahov.models.Roles;
import com.kvlahov.models.RolesPK;

/**
 *
 * @author evlakre
 */
public interface IRolesRepository extends IRepository<Roles, RolesPK>{
    boolean userInRole(String username, String role);
}
