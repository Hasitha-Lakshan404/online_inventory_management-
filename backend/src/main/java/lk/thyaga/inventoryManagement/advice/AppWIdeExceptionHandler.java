package lk.thyaga.inventoryManagement.advice;

import lk.thyaga.inventoryManagement.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/6/2023
 * Time :3:03 AM
 */

@RestControllerAdvice
@CrossOrigin
public class AppWIdeExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({RuntimeException.class})
    public ResponseUtil handleException(RuntimeException e){
        return new ResponseUtil("500",e.getMessage(),null);
    }
}
