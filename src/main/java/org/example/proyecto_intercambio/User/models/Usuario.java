package org.example.proyecto_intercambio.User.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(max = 45, message = "El nombre de usuario no debe exceder los 45 caracteres")
    @Column(name = "userName", length = 45, nullable = false)
    private String userName;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato de email es inválido")
    @Size(max = 150, message = "El email no debe exceder los 150 caracteres")
    @Column(name = "email", length = 150, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(max = 200, message = "La contraseña no debe exceder los 200 caracteres")
    @Column(name = "password", length = 200, nullable = false)
    private String password;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 45, message = "El nombre no debe exceder los 45 caracteres")
    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    @Size(max = 100, message = "Los apellidos no deben exceder los 100 caracteres")
    @Column(name = "lastName", length = 100, nullable = false)
    private String lastName;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\d{10,12}$", message = "El teléfono debe tener entre 10 y 12 dígitos numéricos")
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
