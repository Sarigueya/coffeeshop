package coffeeshop.inventorysystem.kardex.service;

import coffeeshop.inventorysystem.auth.model.User;
import coffeeshop.inventorysystem.common.CafeConstants;
import coffeeshop.inventorysystem.common.CafeUtils;
import coffeeshop.inventorysystem.ingrediente.model.Ingrediente;
import coffeeshop.inventorysystem.ingrediente.repository.IngredienteDao;
import coffeeshop.inventorysystem.kardex.dto.MovimientoRequest;
import coffeeshop.inventorysystem.kardex.model.Kardex;
import coffeeshop.inventorysystem.kardex.model.Movimiento;
import coffeeshop.inventorysystem.kardex.repository.KardexDao;
import coffeeshop.inventorysystem.kardex.repository.MovimientoDao;
import coffeeshop.inventorysystem.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KardexServiceImpl implements KardexService {

    private final KardexDao kardexDao;

    private final IngredienteDao ingredienteDao;

    private final MovimientoDao movimientoDao;

    private final JwtFilter jwtFilter;

    private Double obtenerSaldoActual(Integer ingredienteId) {
        List<Kardex> historial = kardexDao.findByIngredienteId(ingredienteId);
        if (historial.isEmpty()) return 0.0;
        return historial.get(historial.size() - 1).getSaldoNuevo();
    }

    @Override
    public ResponseEntity<String> registrarEntrada(MovimientoRequest request) {
        try {
            Optional<Ingrediente> ingredienteOpt = ingredienteDao.findById(request.getIngredienteId());
            Optional<Movimiento> movimientoOpt = movimientoDao.findById(request.getMovimientoId());

            if (ingredienteOpt.isEmpty()) {
                return CafeUtils.getResponseEntity("Ingrediente no encontrado.", HttpStatus.BAD_REQUEST);
            }
            if (movimientoOpt.isEmpty()) {
                return CafeUtils.getResponseEntity("Movimiento no encontrado.", HttpStatus.BAD_REQUEST);
            }

            Double saldoAnterior = obtenerSaldoActual(request.getIngredienteId());
            Double saldoNuevo = saldoAnterior + request.getCantidad();

            Kardex kardex = new Kardex();
            kardex.setIngrediente(ingredienteOpt.get());
            kardex.setMovimiento(movimientoOpt.get());
            kardex.setUsuario(new User());
            kardex.getUsuario().setId(Integer.valueOf(jwtFilter.getCurrentUser()));
            kardex.setCantidad(request.getCantidad());
            kardex.setSaldoAnterior(saldoAnterior);
            kardex.setSaldoNuevo(saldoNuevo);
            kardex.setFecha(LocalDateTime.now());

            kardexDao.save(kardex);
            return CafeUtils.getResponseEntity("Entrada registrada exitosamente.", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> registrarSalida(MovimientoRequest request) {
        try {
            Optional<Ingrediente> ingredienteOpt = ingredienteDao.findById(request.getIngredienteId());
            Optional<Movimiento> movimientoOpt = movimientoDao.findById(request.getMovimientoId());

            if (ingredienteOpt.isEmpty()) {
                return CafeUtils.getResponseEntity("Ingrediente no encontrado.", HttpStatus.BAD_REQUEST);
            }
            if (movimientoOpt.isEmpty()) {
                return CafeUtils.getResponseEntity("Movimiento no encontrado.", HttpStatus.BAD_REQUEST);
            }

            Double saldoAnterior = obtenerSaldoActual(request.getIngredienteId());

            if (saldoAnterior < request.getCantidad()) {
                return CafeUtils.getResponseEntity("Stock insuficiente.", HttpStatus.BAD_REQUEST);
            }

            Double saldoNuevo = saldoAnterior - request.getCantidad();

            Kardex kardex = new Kardex();
            kardex.setIngrediente(ingredienteOpt.get());
            kardex.setMovimiento(movimientoOpt.get());
            kardex.setUsuario(new User());
            kardex.getUsuario().setId(Integer.valueOf(jwtFilter.getCurrentUser()));
            kardex.setCantidad(request.getCantidad());
            kardex.setSaldoAnterior(saldoAnterior);
            kardex.setSaldoNuevo(saldoNuevo);
            kardex.setFecha(LocalDateTime.now());

            kardexDao.save(kardex);
            return CafeUtils.getResponseEntity("Salida registrada exitosamente.", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
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
}
