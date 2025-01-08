package org.example.proyecto_intercambio.Intercambio.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.proyecto_intercambio.Participantes.models.ParticipantesModel;
import org.example.proyecto_intercambio.Temas.models.TemaModel;
import org.example.proyecto_intercambio.User.models.Usuario;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Intercambios")
public class IntercambioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIntercambio;

    @Column(name = "idInvitacion", length = 30)
    private String idInvitacion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "intercambio", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"intercambio", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private List<ParticipantesModel> participantes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "intercambio", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"intercambio", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private List<TemaModel> temas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdBy", nullable = false)
    @JsonIgnoreProperties(value = {"intercambios", "password", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Usuario createdBy;

    @Positive(message = "Monto Máximo debe ser un valor positivo")
    @Column(name = "montoMaximo", nullable = false)
    private double montoMaximo;

    @Column(name = "fechaExpiracion", nullable = false)
    private Date fechaExpiracion;

    @Override
    public String toString() {
        return "IntercambioModel{" +
                "idIntercambio=" + idIntercambio +
                ", idInvitacion='" + idInvitacion + '\'' +
                ", montoMaximo=" + montoMaximo +
                ", fechaExpiracion=" + fechaExpiracion +
                '}';
    }

    public Long getIdIntercambio() {
        return idIntercambio;
    }

    public void setIdIntercambio(Long idIntercambio) {
        this.idIntercambio = idIntercambio;
    }

    public String getIdInvitacion() {
        return idInvitacion;
    }

    public void setIdInvitacion(String idInvitacion) {
        this.idInvitacion = idInvitacion;
    }

    public double getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public List<TemaModel> getTemas() {
        return temas;
    }

    public void setTemas(List<TemaModel> temas) {
        this.temas = temas;
    }

    public List<ParticipantesModel> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<ParticipantesModel> participantes) {
        this.participantes = participantes;
    }

    public Usuario getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Usuario createdBy) {
        this.createdBy = createdBy;
    }
}
