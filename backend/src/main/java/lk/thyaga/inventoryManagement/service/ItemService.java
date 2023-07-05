package lk.thyaga.inventoryManagement.service;

import lk.thyaga.inventoryManagement.dto.ItemDTO;

import java.util.List;

/**
 * @author : Hasitha Lakshan
 * Project Name: thyaga_task
 * Date        : 7/6/2023
 * Time        : 3:17 AM
 */

public interface ItemService {
    void saveItem(ItemDTO dto);
    public List<ItemDTO> getAllItems();
    void updateItem(ItemDTO item);
}
