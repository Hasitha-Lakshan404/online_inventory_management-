package lk.thyaga.inventoryManagement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author : Hasitha Lakshan
 * Project :thyaga_task
 * Date :7/5/2023
 * Time :11:13 PM
 */

@SpringBootApplication
public class WebAppInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebAppInitializer.class);
    }

    @Bean
    public ModelMapper modelmapper() {
        return new ModelMapper();
    }

}
