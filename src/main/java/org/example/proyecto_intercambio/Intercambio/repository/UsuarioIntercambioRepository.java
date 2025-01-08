package org.example.proyecto_intercambio.Intercambio.repository;

import org.example.proyecto_intercambio.Intercambio.models.UserIntercambioModel;
import org.example.proyecto_intercambio.User.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioIntercambioRepository extends JpaRepository<UserIntercambioModel, Long> {
    public List<UserIntercambioModel> findByUsuario(Usuario usuario);
}
