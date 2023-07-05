package lk.thyaga.inventoryManagement.controller;

import lk.thyaga.inventoryManagement.dto.ItemDTO;
import lk.thyaga.inventoryManagement.service.ItemService;
import lk.thyaga.inventoryManagement.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/6/2023
 * Time :2:59 AM
 */

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemService service;

    @PostMapping
    public ResponseUtil saveEmployee(@RequestBody ItemDTO dto) {
        service.saveItem(dto);
        return new ResponseUtil("200", "Successfully Saved.", null);
    }

    @GetMapping(path = "allItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllItems() {
        return new ResponseUtil("200", "Done", service.getAllItems());
    }

}
