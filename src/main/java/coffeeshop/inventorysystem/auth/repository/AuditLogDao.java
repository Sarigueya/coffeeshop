package coffeeshop.inventorysystem.auth.repository;

import coffeeshop.inventorysystem.auth.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogDao extends JpaRepository<AuditLog, Integer> {
}
