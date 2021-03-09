package cz.rk.eshop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * E-Shop application - spring boot rest api
 */
@SpringBootApplication
@ComponentScan({"cz.rk.eshop.service"})
@EntityScan("cz.rk.eshop.entity")
@EnableJpaRepositories("cz.rk.eshop.repository")
public class EShopApp {

    public static void main(String[] args) {
        SpringApplication.run(EShopApp.class, args);
    }

}
