package coffeeshop.inventorysystem.auth.service;

import coffeeshop.inventorysystem.auth.model.AuditLog;
import coffeeshop.inventorysystem.auth.model.User;
import coffeeshop.inventorysystem.auth.repository.AuditLogDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
