package org.example.proyecto_intercambio.User.service.impl;

import org.example.proyecto_intercambio.User.models.Usuario;
import org.example.proyecto_intercambio.User.repository.UserRepository;
import org.example.proyecto_intercambio.User.service.UserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = userRepository.findByUserName(username);
        if (user.isPresent()) {
            Usuario objUser = user.get();
            return User.builder()
                    .username(objUser.getUserName())
                    .password(objUser.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    @Override
    public List<Usuario> readAllUsers() {
        return (List<Usuario>) userRepository.findAll();
    }

    @Override
    public Usuario readUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado")
        );
    }

    @Override
    public Usuario createUser(Usuario user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
