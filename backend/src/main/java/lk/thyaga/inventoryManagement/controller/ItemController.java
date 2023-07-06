package lk.thyaga.inventoryManagement.controller;

import lk.thyaga.inventoryManagement.dto.ItemDTO;
import lk.thyaga.inventoryManagement.service.ItemService;
import lk.thyaga.inventoryManagement.util.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ItemController {

    private final ItemService service;

    @PostMapping
    public ResponseUtil saveItem(@RequestBody ItemDTO dto) {
        service.saveItem(dto);
        return new ResponseUtil("200", "Successfully Saved.", null);
    }

    @GetMapping(path = "allItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllItems() {
        return new ResponseUtil("200", "Done", service.getAllItems());
    }

    @PutMapping
    public ResponseUtil updateItem(@RequestBody ItemDTO item) {
        service.updateItem(item);
        return new ResponseUtil("200", "Successfully Updated.", null);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteItem(@RequestParam long id){
        service.deleteItem(id);
        return new ResponseUtil("200",id+" :Item Deleted.!",null);
    }

    @GetMapping("/searchItem")
    public ResponseUtil searchItem(@RequestParam("val") String searchWord) {
        return new ResponseUtil("200","Done",service.searchByAll(searchWord));
    }

}
