package coffeeshop.inventorysystem.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Clase utilitaria con métodos comunes para toda la aplicación.
 *
 * @since 1.0
 */
public class CafeUtils {

    private CafeUtils() {
    }

    /**
     * Construye una respuesta HTTP con un mensaje JSON estándar.
     *
     * @param responseMessage mensaje descriptivo del resultado
     * @param httpStatus      estado HTTP de la respuesta
     * @return respuesta con formato {@code {"message": "..."}}
     */
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus) {
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}", httpStatus);
    }
}
