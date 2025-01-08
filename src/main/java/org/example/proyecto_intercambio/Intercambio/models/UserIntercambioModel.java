package org.example.proyecto_intercambio.Intercambio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.proyecto_intercambio.User.models.Usuario;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "Usuario_Intercambios")
public class UserIntercambioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long idIntercambio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "intercambio", nullable = false)
    private IntercambioModel intercambio;

    public Long getIdIntercambio() {
        return idIntercambio;
    }

    public void setIdIntercambio(Long idIntercambio) {
        this.idIntercambio = idIntercambio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public IntercambioModel getIntercambio() {
        return intercambio;
    }

    public void setIntercambio(IntercambioModel intercambio) {
        this.intercambio = intercambio;
    }
}
