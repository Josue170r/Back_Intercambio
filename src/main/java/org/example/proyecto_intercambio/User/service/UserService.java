package org.example.proyecto_intercambio.User.service;

import org.example.proyecto_intercambio.User.models.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<Usuario> readAllUsers();
    Usuario readUserById(Long id);
    Usuario findByUserName(String username);
    Usuario createUser(Usuario user);
    void deleteUserById(Long id);
}
