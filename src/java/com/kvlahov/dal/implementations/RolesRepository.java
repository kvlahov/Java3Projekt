/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IRolesRepository;
import com.kvlahov.models.Roles;
import com.kvlahov.models.RolesPK;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public class RolesRepository extends GenericRepository<Roles, RolesPK> implements IRolesRepository {

    public RolesRepository(EntityManager entityManager) {
        super(entityManager, Roles.class);
    }

    @Override
    public boolean userInRole(String username, String role) {
        return entityManager.createNamedQuery("Roles.findByUsername", Roles.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .anyMatch(r -> r.getRolesPK().getRolename().equals(role));
    }
    
}
