/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import com.kvlahov.dal.implementations.GenericRepository;
import com.kvlahov.models.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author evlakre
 */
public class GenericRepositoryTest {
    private IRepository<Users, Integer> repo;
    
    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaProjektPU");
        EntityManager entityManager = emf.createEntityManager();
        repo = new GenericRepository<>(entityManager, Users.class);
    }
    

     @Test
     public void getAll_shouldNotBeEmpty() {
         List<Users> users = repo.getAll();
         assertFalse(users.isEmpty());
     }
}
