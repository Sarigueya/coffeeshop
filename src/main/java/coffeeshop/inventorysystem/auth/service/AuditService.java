package coffeeshop.inventorysystem.auth.service;

import coffeeshop.inventorysystem.auth.model.AuditLog;
import coffeeshop.inventorysystem.auth.model.User;
import coffeeshop.inventorysystem.auth.repository.AuditLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Servicio de auditoría para registrar acciones importantes del sistema.
 * <p>
 * Persiste un registro en {@link coffeeshop.inventorysystem.auth.model.AuditLog}
 * con el usuario, la acción realizada y un detalle descriptivo.
 * </p>
 *
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogDao auditLogDao;

    public void log(User usuario, String accion, String detalle) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUsuario(usuario);
        auditLog.setAccion(accion);
        auditLog.setDetalle(detalle);
        auditLog.setFecha(LocalDateTime.now());
        auditLogDao.save(auditLog);
    }
}
