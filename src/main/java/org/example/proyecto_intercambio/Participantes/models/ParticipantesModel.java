package org.example.proyecto_intercambio.Participantes.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.proyecto_intercambio.Intercambio.models.IntercambioModel;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Participantes")
public class ParticipantesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParticipante;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 45, message = "El nombre no debe exceder los 45 caracteres")
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato de email es inválido")
    @Size(max = 150, message = "El email no debe exceder los 150 caracteres")
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^\\d{10,12}$", message = "El teléfono debe tener entre 10 y 12 dígitos numéricos")
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intercambio", nullable = false)
    private IntercambioModel intercambio;

    public Long getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(Long idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public IntercambioModel getIntercambio() {
        return intercambio;
    }

    public void setIntercambio(IntercambioModel intercambio) {
        this.intercambio = intercambio;
    }

    @Override
    public String toString() {
        return "ParticipantesModel{" +
                "idParticipante=" + idParticipante +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
