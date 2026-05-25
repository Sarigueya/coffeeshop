package coffeeshop.inventorysystem.ingrediente.service;

import coffeeshop.inventorysystem.ingrediente.dto.UnidadMedidaRequest;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Servicio para la gestión de unidades de medida.
 * <p>
 * Define las operaciones CRUD para las unidades de medida
 * utilizadas por los ingredientes (ej: gramos, mililitros, unidades).
 * </p>
 *
 * @since 1.0
 */
public interface UnidadMedidaService {

    /**
     * Crea una nueva unidad de medida.
     *
     * @param request datos de la unidad de medida a crear
     * @return {@code 200 OK} si se creó exitosamente
     */
    ResponseEntity<String> create(UnidadMedidaRequest request);

    /**
     * Actualiza una unidad de medida existente.
     *
     * @param request datos actualizados, incluye {@code id} obligatorio
     * @return {@code 200 OK} si se actualizó,
     *         {@code 400 BAD_REQUEST} si no se encuentra
     */
    ResponseEntity<String> update(UnidadMedidaRequest request);

    /**
     * Elimina una unidad de medida.
     *
     * @param id identificador de la unidad de medida
     * @return {@code 200 OK} si se eliminó,
     *         {@code 400 BAD_REQUEST} si no existe
     */
    ResponseEntity<String> delete(Integer id);

    /**
     * Obtiene una unidad de medida por su identificador.
     *
     * @param id identificador de la unidad
     * @return la unidad con {@code 200 OK}, o {@code 404 NOT_FOUND} si no existe
     */
    ResponseEntity<UnidadMedida> getById(Integer id);

    /**
     * Obtiene todas las unidades de medida registradas.
     *
     * @return lista de unidades de medida
     */
    ResponseEntity<List<UnidadMedida>> getAll();
}
