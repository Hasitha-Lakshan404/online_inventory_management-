package lk.thyaga.inventoryManagement.service.impl;

import lk.thyaga.inventoryManagement.dto.ItemDTO;
import lk.thyaga.inventoryManagement.entity.Item;
import lk.thyaga.inventoryManagement.repo.ItemRepo;
import lk.thyaga.inventoryManagement.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ItemDTO> getAllItems() {
        return mapper.map(repo.findAll(), new TypeToken<List<ItemDTO>>() {
        }.getType());
    }

    @Override
    public void updateItem(ItemDTO item) {
        repo.save(mapper.map(item, Item.class));
    }

    @Override
    public void deleteItem(long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Item " + id + " Not Available to Delete..!");
        }
        repo.deleteById(id);
    }
}


