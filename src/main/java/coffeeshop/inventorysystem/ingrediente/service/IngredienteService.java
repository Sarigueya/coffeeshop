package coffeeshop.inventorysystem.ingrediente.service;

import coffeeshop.inventorysystem.ingrediente.dto.IngredienteRequest;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Servicio para la gestión de ingredientes.
 * <p>
 * Define las operaciones CRUD sobre ingredientes, incluyendo
 * validaciones de nombre único y gestión de unidades de medida.
 * Al eliminar, si el ingrediente tiene movimientos asociados,
 * se desactiva en lugar de eliminarse físicamente.
 * </p>
 *
 * @since 1.0
 */
public interface IngredienteService {

    /**
     * Crea un nuevo ingrediente.
     *
     * @param request datos del ingrediente a crear
     * @return {@code 200 OK} si se creó exitosamente,
     *         {@code 400 BAD_REQUEST} si el nombre ya existe o unidad de medida no encontrada
     */
    ResponseEntity<String> create(IngredienteRequest request);

    /**
     * Actualiza un ingrediente existente.
     *
     * @param request datos actualizados del ingrediente, incluye {@code id} obligatorio
     * @return {@code 200 OK} si se actualizó exitosamente,
     *         {@code 400 BAD_REQUEST} si no se encuentra o hay duplicados
     */
    ResponseEntity<String> update(IngredienteRequest request);

    /**
     * Elimina o desactiva un ingrediente.
     *
     * @param id identificador del ingrediente
     * @return {@code 200 OK} si se eliminó o desactivó,
     *         {@code 400 BAD_REQUEST} si no existe
     */
    ResponseEntity<String> delete(Integer id);

    /**
     * Obtiene un ingrediente por su identificador.
     *
     * @param id identificador del ingrediente
     * @return el ingrediente con {@code 200 OK}, o {@code 404 NOT_FOUND} si no existe
     */
    ResponseEntity<Ingrediente> getById(Integer id);

    /**
     * Obtiene todos los ingredientes registrados.
     *
     * @return lista de ingredientes
     */
    ResponseEntity<List<Ingrediente>> getAll();
}
