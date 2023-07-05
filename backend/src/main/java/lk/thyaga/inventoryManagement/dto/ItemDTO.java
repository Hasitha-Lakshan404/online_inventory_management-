package lk.thyaga.inventoryManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/6/2023
 * Time :2:57 AM
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemDTO {
    private long itemId;
    private String itemName;
    private String itemType;
    private String quantity;
    private double unitPrice;
    private String description;
}
