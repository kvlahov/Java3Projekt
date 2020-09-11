/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal.implementations;

import com.kvlahov.dal.IRepository;
import com.kvlahov.dal.IUsersRepository;
import com.kvlahov.models.Users;
import com.kvlahov.common.Utilities;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author evlakre
 */
public class UsersRepository extends GenericRepository<Users, Long> implements IUsersRepository {

    public UsersRepository(EntityManager entityManager) {
        super(entityManager, Users.class);
    }
    
    @Override
    public Optional<Users> getByUsername(String username) {
        return entityManager
                .createNamedQuery("Users.findByUsername", Users.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public boolean userExists(String username, String password) {
        return !entityManager.createQuery("SELECT u FROM Users u WHERE u.username = :username and u.passwordhash = :passwordhash")
                .setParameter("username", username)
                .setParameter("passwordhash", password)
                .getResultList()
                .isEmpty();
    }
    
}
