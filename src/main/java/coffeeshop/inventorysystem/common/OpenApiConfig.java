package coffeeshop.inventorysystem.common;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de OpenAPI/Swagger para la documentación de la API.
 * <p>
 * Define el esquema de seguridad JWT (bearer token), la información
 * de la API y los servidores disponibles. La descripción y título
 * se obtienen de los mensajes i18n.
 * </p>
 *
 * @since 1.0
 */
@Configuration
public class OpenApiConfig {

    private final MessageSource messageSource;

    public OpenApiConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public OpenAPI inventorySystemOpenAPI() {
        var locale = LocaleContextHolder.getLocale();

        return new OpenAPI()
                .addServersItem(new Server()
                        .url("https://localhost:8080")
                        .description(messageSource.getMessage("swagger.server.local", null, locale)))
                .info(new Info()
                        .title(messageSource.getMessage("swagger.title", null, locale))
                        .description(messageSource.getMessage("swagger.description", null, locale))
                        .version("1.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description(messageSource.getMessage("swagger.security.description", null, locale))))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
