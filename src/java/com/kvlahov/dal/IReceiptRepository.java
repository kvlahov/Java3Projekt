/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import com.kvlahov.models.Receipt;
import com.kvlahov.models.Users;
import java.util.List;

/**
 *
 * @author evlakre
 */
public interface IReceiptRepository extends IRepository<Receipt, Long>  {
    List<Receipt> getReceiptsForUser(Users user);
}
