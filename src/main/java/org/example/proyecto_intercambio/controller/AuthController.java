package org.example.proyecto_intercambio.controller;
import org.example.proyecto_intercambio.User.models.LoginRequest;
import org.example.proyecto_intercambio.User.models.Usuario;
import org.example.proyecto_intercambio.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = {"*"})
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Usuario userResult = userService.findByUserName(userDetails.getUsername());
            userResult.setPassword(null);
            return userResult;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Credenciales inválidas");
        }
    }

    @PostMapping(value = "/signup", consumes = "application/json")
    public Usuario createUser(@RequestBody Usuario user){
        System.out.println(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.createUser(user);
    }
}