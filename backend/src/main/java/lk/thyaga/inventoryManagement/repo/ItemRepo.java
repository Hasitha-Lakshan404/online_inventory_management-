package lk.thyaga.inventoryManagement.repo;

import lk.thyaga.inventoryManagement.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/6/2023
 * Time :3:19 AM
 */

public interface ItemRepo extends JpaRepository<Item,Long> {
}
