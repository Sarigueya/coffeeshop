package coffeeshop.inventorysystem;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Inicializador de servlet para despliegue en contenedores externos (WAR).
 * <p>
 * Permite que la aplicación Spring Boot se ejecute en un servidor
 * de aplicaciones tradicional en lugar del embebido.
 * </p>
 *
 * @since 1.0
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InventorySystemApplication.class);
    }

}
