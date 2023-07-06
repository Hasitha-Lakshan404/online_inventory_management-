package lk.thyaga.inventoryManagement.repo;

import lk.thyaga.inventoryManagement.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/6/2023
 * Time :3:19 AM
 */

public interface ItemRepo extends JpaRepository<Item,Long> {

    @Query(value = "SELECT * FROM item WHERE item_id LIKE %:searchWord% OR item_name LIKE %:searchWord% OR item_type LIKE %:searchWord% OR unit_price LIKE %:searchWord%", nativeQuery = true)
    List<Item> searchByAll(@Param("searchWord") String searchWord);
}
