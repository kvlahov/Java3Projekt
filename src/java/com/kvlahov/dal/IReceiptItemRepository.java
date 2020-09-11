/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kvlahov.dal;

import com.kvlahov.models.Receipt;
import com.kvlahov.models.ReceiptItem;
import java.util.List;

/**
 *
 * @author evlakre
 */
public interface IReceiptItemRepository extends IRepository<ReceiptItem, Long> {
    public List<ReceiptItem> getByReceipt(Receipt receipt);
}
