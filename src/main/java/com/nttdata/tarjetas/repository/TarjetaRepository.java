package com.nttdata.tarjetas.repository;

import com.nttdata.tarjetas.model.Tarjeta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda las tarjetas en una lista en memoria (no hay base de datos).
 * Al reiniciar la aplicación, los datos vuelven a su estado inicial.
 */
@Repository
public class TarjetaRepository {

    private final List<Tarjeta> tarjetas = new ArrayList<>();

    public TarjetaRepository() {
        tarjetas.add(new Tarjeta("4111000000000001", "Ana Torres", "CREDITO", 5000.00, "ACTIVA"));
        tarjetas.add(new Tarjeta("4111000000000002", "Luis Ramos", "DEBITO", 0.00, "ACTIVA"));
        tarjetas.add(new Tarjeta("4111000000000003", "María Quispe", "CREDITO", 12000.00, "BLOQUEADA"));
        tarjetas.add(new Tarjeta("4111000000000004", "Carlos Díaz", "CREDITO", 3000.00, "ACTIVA"));
        tarjetas.add(new Tarjeta("4111000000000005", "Rosa Huamán", "DEBITO", 0.00, "ACTIVA"));
    }

    /** Devuelve todas las tarjetas. */
    public List<Tarjeta> listarTodas() {
        return tarjetas;
    }

    /** Busca una tarjeta por su número. */
    public Tarjeta buscarPorNumero(String numero) {
        // Recorre la lista para encontrar el número solicitado.
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getNumero().equals(numero)) {
                return tarjeta;
            }
        }

        // Devuelve null cuando no existe una tarjeta con ese número.
        return null;
    }

    /** Agrega una tarjeta nueva a la lista. */
    public void guardar(Tarjeta tarjeta) {
        // Guarda la tarjeta en memoria.
        tarjetas.add(tarjeta);
    }
}
