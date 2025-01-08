package org.example.proyecto_intercambio.Temas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.proyecto_intercambio.Intercambio.models.IntercambioModel;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Temas")
public class TemaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTema;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no debe exceder los 50 caracteres")
    @Column(name = "nombreCategoria", length = 50, nullable = false)
    private String nombreTema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "intercambio", nullable = false)
    private IntercambioModel intercambio;

    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public IntercambioModel getIntercambio() {
        return intercambio;
    }

    public void setIntercambio(IntercambioModel intercambio) {
        this.intercambio = intercambio;
    }
}
