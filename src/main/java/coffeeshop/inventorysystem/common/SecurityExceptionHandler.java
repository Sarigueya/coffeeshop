package coffeeshop.inventorysystem.common;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Manejador global de excepciones de seguridad.
 * <p>
 * Captura las excepciones de acceso denegado ({@link org.springframework.security.access.AccessDeniedException})
 * y devuelve una respuesta HTTP 403 con un mensaje i18n.
 * </p>
 *
 * @since 1.0
 */
@RestControllerAdvice
public class SecurityExceptionHandler {

    private final MessageSource messageSource;

    public SecurityExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        return CafeUtils.getResponseEntity(
                messageSource.getMessage("common.unauthorized.access", null, LocaleContextHolder.getLocale()),
                HttpStatus.FORBIDDEN);
    }
}
