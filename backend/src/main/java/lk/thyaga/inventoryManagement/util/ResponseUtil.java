package lk.thyaga.inventoryManagement.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/6/2023
 * Time :3:00 AM
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ResponseUtil {
    private String code;
    private String message;
    private Object data;
}
