/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import com.kvlahov.dal.implementations.UsersRepository;
import com.kvlahov.models.Roles;
import com.kvlahov.models.Roles_;
import com.kvlahov.models.Users;
import com.kvlahov.utilities.Utilities;
import java.util.List;
import java.util.Optional;
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
public class UsersRepositoryTest {

    private UsersRepository usersRepository;

    public UsersRepositoryTest() {
    }

    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaProjektPU");
        EntityManager entityManager = emf.createEntityManager();
        usersRepository = new UsersRepository(entityManager);
    }

    @Test
    public void getAll_shouldNotBeEmpty() {
        List<Users> sut = usersRepository.getAll();
        assertFalse(sut.isEmpty());
    }

    @Test
    public void getById_shouldHaveValue() {
        Optional<Users> sut = usersRepository.getById((long)1);
        assertTrue(sut.isPresent());
    }

    @Test
    public void userExists_shouldBeTrue() {
        boolean sut = usersRepository.userExists("user", "pass");
        assertTrue(sut);
    }
    
    @Test
    public void userExists_shouldBeFalse() {
        boolean sut = usersRepository.userExists("user", "blabla");
        assertFalse(sut);
    }
    
    @Test
    public void getByUsername_shouldHaveValue() {
        Optional<Users> sut = usersRepository.getByUsername("user");
        assertTrue(sut.isPresent());
        assertEquals(sut.get().getUserId(), (Integer)1);
        assertEquals(sut.get().getUsername(), "user");
    }
}
