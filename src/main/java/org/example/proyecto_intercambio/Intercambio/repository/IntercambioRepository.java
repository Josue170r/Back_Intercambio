package org.example.proyecto_intercambio.Intercambio.repository;

import org.example.proyecto_intercambio.Intercambio.models.IntercambioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntercambioRepository extends JpaRepository<IntercambioModel, Long> {
    public IntercambioModel findByIdInvitacion(String idInvitacion);
}
