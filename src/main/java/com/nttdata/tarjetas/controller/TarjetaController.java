package com.nttdata.tarjetas.controller;

import com.nttdata.tarjetas.model.Tarjeta;
import com.nttdata.tarjetas.repository.TarjetaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API REST de tarjetas. Cada método atiende un endpoint.
 */
@RestController
@RequestMapping("/api/v1/tarjetas")
public class TarjetaController {

    private final TarjetaRepository tarjetaRepository;

    public TarjetaController(TarjetaRepository tarjetaRepository) {
        this.tarjetaRepository = tarjetaRepository;
    }

    /** GET /api/v1/tarjetas -> lista todas las tarjetas (200). */
    @GetMapping
    public List<Tarjeta> listarTarjetas() {
        return tarjetaRepository.listarTodas();
    }

    /** GET /api/v1/tarjetas/{numero} -> busca una tarjeta por número. */
    @GetMapping("/{numero}")
    public ResponseEntity<Tarjeta> buscarPorNumero(@PathVariable String numero) {
        // Busca la tarjeta en el repositorio.
        Tarjeta tarjeta = tarjetaRepository.buscarPorNumero(numero);

        if (tarjeta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(tarjeta);
    }

    /** POST /api/v1/tarjetas -> registra una tarjeta nueva. */
    @PostMapping
    public ResponseEntity<Tarjeta> guardarTarjeta(@RequestBody Tarjeta tarjeta) {
        // Comprueba que la petición contiene una tarjeta.
        if (tarjeta == null) {
            return ResponseEntity.badRequest().build();
        }

        // Valida el número de 16 caracteres y que no esté repetido.
        if (tarjeta.getNumero() == null || tarjeta.getNumero().length() != 16) {
            return ResponseEntity.badRequest().build();
        }
        if (tarjetaRepository.buscarPorNumero(tarjeta.getNumero()) != null) {
            return ResponseEntity.status(409).build();
        }

        // Valida los datos obligatorios y las reglas del tipo de tarjeta.
        if (tarjeta.getTitular() == null || tarjeta.getTitular().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        if (!"CREDITO".equals(tarjeta.getTipo()) && !"DEBITO".equals(tarjeta.getTipo())) {
            return ResponseEntity.badRequest().build();
        }
        if ("CREDITO".equals(tarjeta.getTipo()) && tarjeta.getLimite() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        // Las tarjetas nuevas comienzan activas si no se indica el estado.
        if (tarjeta.getEstado() == null) {
            tarjeta.setEstado("ACTIVA");
        }
        if (!"ACTIVA".equals(tarjeta.getEstado())) {
            return ResponseEntity.badRequest().build();
        }

        // Guarda la tarjeta y confirma su creación.
        tarjetaRepository.guardar(tarjeta);
        return ResponseEntity.status(201).body(tarjeta);
    }

    /** PUT /api/v1/tarjetas/{numero}/bloquear -> bloquea una tarjeta. */
    @PutMapping("/{numero}/bloquear")
    public ResponseEntity<Tarjeta> bloquearTarjeta(@PathVariable String numero) {
        // Busca la tarjeta por su número.
        Tarjeta tarjeta = tarjetaRepository.buscarPorNumero(numero);

        if (tarjeta == null) {
            return ResponseEntity.notFound().build();
        }

        // Cambia el estado de la tarjeta.
        tarjeta.setEstado("BLOQUEADA");
        return ResponseEntity.ok(tarjeta);
    }
}
