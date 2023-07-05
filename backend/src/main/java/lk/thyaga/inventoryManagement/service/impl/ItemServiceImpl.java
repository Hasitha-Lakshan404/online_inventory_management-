package lk.thyaga.inventoryManagement.service.impl;

import lk.thyaga.inventoryManagement.dto.ItemDTO;
import lk.thyaga.inventoryManagement.entity.Item;
import lk.thyaga.inventoryManagement.repo.ItemRepo;
import lk.thyaga.inventoryManagement.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/6/2023
 * Time :3:18 AM
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepo repo;

    @Autowired
    ModelMapper mapper;


    @Override
    public void saveItem(ItemDTO dto) {
        if (!repo.existsById(dto.getItemId())) {
            repo.save(mapper.map(dto, Item.class));
        } else {
            throw new RuntimeException(dto.getItemId() + " " + "This Item Already Exists..!");
        }
    }
}
