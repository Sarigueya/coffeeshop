package coffeeshop.inventorysystem;

import coffeeshop.inventorysystem.auth.model.Rol;
import coffeeshop.inventorysystem.auth.repository.RolDao;
import coffeeshop.inventorysystem.ingrediente.model.UnidadMedida;
import coffeeshop.inventorysystem.ingrediente.repository.UnidadMedidaDao;
import coffeeshop.inventorysystem.kardex.model.Movimiento;
import coffeeshop.inventorysystem.kardex.model.TipoMovimiento;
import coffeeshop.inventorysystem.kardex.repository.MovimientoDao;
import coffeeshop.inventorysystem.kardex.repository.TipoMovimientoDao;
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

    @Bean
    CommandLineRunner initUnidades(UnidadMedidaDao unidadMedidaDao) {
        return args -> {
            if (unidadMedidaDao.count() == 0) {
                unidadMedidaDao.save(new UnidadMedida(null, "g"));
                unidadMedidaDao.save(new UnidadMedida(null, "ml"));
                unidadMedidaDao.save(new UnidadMedida(null, "kg"));
                unidadMedidaDao.save(new UnidadMedida(null, "L"));
                unidadMedidaDao.save(new UnidadMedida(null, "unidades"));
            }
        };
    }

    @Bean
    CommandLineRunner initTiposMovimiento(TipoMovimientoDao tipoMovimientoDao, MovimientoDao movimientoDao) {
        return args -> {
            if (tipoMovimientoDao.count() == 0) {
                TipoMovimiento entrada = tipoMovimientoDao.save(new TipoMovimiento(null, "Entrada", "entrada"));
                TipoMovimiento salida = tipoMovimientoDao.save(new TipoMovimiento(null, "Salida", "salida"));

                movimientoDao.save(new Movimiento(null, "Compra", "Compra de insumos", entrada));
                movimientoDao.save(new Movimiento(null, "Venta", "Venta de producto", salida));
                movimientoDao.save(new Movimiento(null, "Daño", "Pérdida o daño de insumo", salida));
            }
        };
    }
}
