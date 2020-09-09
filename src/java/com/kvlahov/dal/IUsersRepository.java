/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import com.kvlahov.models.Users;
import java.io.Serializable;
import java.util.Optional;

/**
 *
 * @author evlakre
 */
public interface IUsersRepository extends IRepository<Users, Long>{
    Optional<Users> getByUsername(String username);
     boolean userExists(String username, String password);
}
