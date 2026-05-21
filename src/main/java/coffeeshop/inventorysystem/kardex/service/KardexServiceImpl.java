package coffeeshop.inventorysystem.kardex.service;

import coffeeshop.inventorysystem.auth.model.User;
import coffeeshop.inventorysystem.auth.repository.UserDao;
import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import coffeeshop.inventorysystem.ingrediente.repository.IngredienteDao;
import coffeeshop.inventorysystem.kardex.dto.KardexSaldoResponse;
import coffeeshop.inventorysystem.kardex.dto.MovimientoRequest;
import coffeeshop.inventorysystem.kardex.dto.VentaRequest;
import coffeeshop.inventorysystem.kardex.model.Kardex;
import coffeeshop.inventorysystem.kardex.model.Movimiento;
import coffeeshop.inventorysystem.kardex.repository.KardexDao;
import coffeeshop.inventorysystem.kardex.repository.MovimientoDao;
import coffeeshop.inventorysystem.producto.model.Producto;
import coffeeshop.inventorysystem.producto.model.RecetaDetalle;
import coffeeshop.inventorysystem.producto.repository.ProductoDao;
import coffeeshop.inventorysystem.producto.repository.RecetaDetalleDao;
import coffeeshop.inventorysystem.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KardexServiceImpl implements KardexService {

    private final KardexDao kardexDao;

    private final IngredienteDao ingredienteDao;

    private final MovimientoDao movimientoDao;

    private final ProductoDao productoDao;

    private final RecetaDetalleDao recetaDetalleDao;

    private final UserDao userDao;

    private final JwtFilter jwtFilter;

    private final MessageSource messageSource;

    private final DataSource dataSource;

    private Double obtenerSaldoActual(Integer ingredienteId) {
        List<Kardex> historial = kardexDao.findByIngredienteId(ingredienteId);
        if (historial.isEmpty()) return 0.0;
        return historial.get(historial.size() - 1).getSaldoNuevo();
    }

    @Override
    public ResponseEntity<String> registrarEntrada(MovimientoRequest request) {
        try {
            Optional<Ingrediente> ingredienteOpt = ingredienteDao.findById(request.getIngredienteId());
            Optional<Movimiento> movimientoOpt = movimientoDao.findByNombreMovimiento("Compra");

            if (ingredienteOpt.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.ingredient.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }
            if (movimientoOpt.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.movement.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }

            Double saldoAnterior = obtenerSaldoActual(request.getIngredienteId());
            Double saldoNuevo = saldoAnterior + request.getCantidad();

            User user = userDao.findByEmailId(jwtFilter.getCurrentUser());
            if (user == null) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.user.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.UNAUTHORIZED);
            }

            Kardex kardex = new Kardex();
            kardex.setIngrediente(ingredienteOpt.get());
            kardex.setMovimiento(movimientoOpt.get());
            kardex.setUsuario(user);
            kardex.setCantidad(request.getCantidad());
            kardex.setSaldoAnterior(saldoAnterior);
            kardex.setSaldoNuevo(saldoNuevo);
            kardex.setFecha(LocalDateTime.now());

            kardexDao.save(kardex);
            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("kardex.entrada.success", null, LocaleContextHolder.getLocale()),
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> registrarSalida(MovimientoRequest request) {
        try {
            Optional<Ingrediente> ingredienteOpt = ingredienteDao.findById(request.getIngredienteId());
            Optional<Movimiento> movimientoOpt = movimientoDao.findByNombreMovimiento("Daño");

            if (ingredienteOpt.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.ingredient.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }
            if (movimientoOpt.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.movement.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }

            Double saldoAnterior = obtenerSaldoActual(request.getIngredienteId());

            if (saldoAnterior < request.getCantidad()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.stock.insufficient", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }

            Double saldoNuevo = saldoAnterior - request.getCantidad();

            User user = userDao.findByEmailId(jwtFilter.getCurrentUser());
            if (user == null) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.user.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.UNAUTHORIZED);
            }

            Kardex kardex = new Kardex();
            kardex.setIngrediente(ingredienteOpt.get());
            kardex.setMovimiento(movimientoOpt.get());
            kardex.setUsuario(user);
            kardex.setCantidad(request.getCantidad());
            kardex.setSaldoAnterior(saldoAnterior);
            kardex.setSaldoNuevo(saldoNuevo);
            kardex.setFecha(LocalDateTime.now());

            kardexDao.save(kardex);

            Ingrediente ingrediente = ingredienteOpt.get();
            String mensaje = messageSource.getMessage("kardex.salida.success", null, LocaleContextHolder.getLocale());
            if (saldoNuevo < ingrediente.getStockMinimo()) {
                mensaje += messageSource.getMessage("kardex.salida.low.stock",
                        new Object[]{ingrediente.getNombre(), saldoNuevo, ingrediente.getStockMinimo()},
                        LocaleContextHolder.getLocale());
            }

            return CafeUtils.getResponseEntity(mensaje, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> vender(VentaRequest request) {
        try {
            Optional<Producto> productoOpt = productoDao.findById(request.getProductoId());
            if (productoOpt.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.product.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }
            if (productoOpt.get().getReceta() == null) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.product.no.recipe", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }

            Optional<Movimiento> movimientoOpt = movimientoDao.findByNombreMovimiento("Venta");
            if (movimientoOpt.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.movement.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }

            List<RecetaDetalle> detalles = recetaDetalleDao.findByRecetaId(
                    productoOpt.get().getReceta().getId()
            );

            if (detalles.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.recipe.no.ingredients", null, LocaleContextHolder.getLocale()),
                        HttpStatus.BAD_REQUEST);
            }

            var locale = LocaleContextHolder.getLocale();
            List<String> erroresStock = new ArrayList<>();
            for (RecetaDetalle detalle : detalles) {
                Double necesario = request.getCantidad() * detalle.getCantidadRequerida();
                Double saldoActual = obtenerSaldoActual(detalle.getIngrediente().getId());
                Double saldoFinal = saldoActual - necesario;
                if (saldoActual < necesario) {
                    erroresStock.add(messageSource.getMessage("kardex.stock.insufficient.detail",
                            new Object[]{detalle.getIngrediente().getNombre(), necesario, saldoActual}, locale));
                } else if (saldoFinal < detalle.getIngrediente().getStockMinimo()) {
                    erroresStock.add(messageSource.getMessage("kardex.stock.below.minimum",
                            new Object[]{detalle.getIngrediente().getNombre(), saldoFinal,
                                    detalle.getIngrediente().getStockMinimo()}, locale));
                }
            }

            if (!erroresStock.isEmpty()) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.sale.impossible", null, LocaleContextHolder.getLocale())
                                + "\n" + String.join("\n", erroresStock),
                        HttpStatus.BAD_REQUEST
                );
            }

            User user = userDao.findByEmailId(jwtFilter.getCurrentUser());
            if (user == null) {
                return CafeUtils.getResponseEntity(
                        messageSource.getMessage("kardex.user.not.found", null, LocaleContextHolder.getLocale()),
                        HttpStatus.UNAUTHORIZED);
            }

            for (RecetaDetalle detalle : detalles) {
                Double necesario = request.getCantidad() * detalle.getCantidadRequerida();
                Double saldoAnterior = obtenerSaldoActual(detalle.getIngrediente().getId());
                Double saldoNuevo = saldoAnterior - necesario;

                Kardex kardex = new Kardex();
                kardex.setIngrediente(detalle.getIngrediente());
                kardex.setMovimiento(movimientoOpt.get());
                kardex.setUsuario(user);
                kardex.setCantidad(necesario);
                kardex.setSaldoAnterior(saldoAnterior);
                kardex.setSaldoNuevo(saldoNuevo);
                kardex.setFecha(LocalDateTime.now());

                kardexDao.save(kardex);
            }

            return CafeUtils.getResponseEntity(
                    messageSource.getMessage("kardex.venta.success", null, LocaleContextHolder.getLocale()),
                    HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<KardexSaldoResponse>> getSaldos() {
        try {
            List<Ingrediente> ingredientes = ingredienteDao.findAll();
            List<KardexSaldoResponse> saldos = new ArrayList<>();
            for (Ingrediente ing : ingredientes) {
                Double saldo = obtenerSaldoActual(ing.getId());
                saldos.add(new KardexSaldoResponse(ing.getId(), ing.getNombre(), saldo));
            }
            return new ResponseEntity<>(saldos, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Kardex>> getByIngrediente(Integer ingredienteId) {
        try {
            return new ResponseEntity<>(kardexDao.findByIngredienteId(ingredienteId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Kardex>> getAll() {
        try {
            return new ResponseEntity<>(kardexDao.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<byte[]> generarReportePdf() {
        try {
            ClassPathResource resource = new ClassPathResource("rpt_kardex_insumos_empleados.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());

            try (Connection connection = dataSource.getConnection()) {
                JasperPrint jasperPrint = JasperFillManager.fillReport(
                        jasperReport, new HashMap<>(), connection);
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDisposition(
                        ContentDisposition.attachment().filename("kardex.pdf").build());

                return ResponseEntity.ok().headers(headers).body(pdfBytes);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
