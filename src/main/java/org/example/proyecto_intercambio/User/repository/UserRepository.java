package org.example.proyecto_intercambio.User.repository;

import org.example.proyecto_intercambio.User.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUserName(String userName);
}
