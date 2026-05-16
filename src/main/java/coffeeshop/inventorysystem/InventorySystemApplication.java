package coffeeshop.inventorysystem;

import coffeeshop.inventorysystem.POJO.Rol;
import coffeeshop.inventorysystem.dao.RolDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventorySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventorySystemApplication.class, args);
    }

    @Bean
    CommandLineRunner initRoles(RolDao rolDao) {
        return args -> {
            if (rolDao.findByNombre("admin") == null) {
                rolDao.save(new Rol(null, "admin"));
            }
            if (rolDao.findByNombre("user") == null) {
                rolDao.save(new Rol(null, "user"));
            }
        };
    }
}
