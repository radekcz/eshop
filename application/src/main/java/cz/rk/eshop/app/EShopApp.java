package cz.rk.eshop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * E-Shop application - spring boot rest api
 */
@SpringBootApplication
@ComponentScan({"cz.rk.eshop.service"})
public class EShopApp {

    public static void main(String[] args) {
        SpringApplication.run(EShopApp.class, args);
    }

}
