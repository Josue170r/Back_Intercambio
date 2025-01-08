package org.example.proyecto_intercambio.Intercambio.service;

import org.example.proyecto_intercambio.Intercambio.models.IntercambioModel;
import org.example.proyecto_intercambio.Intercambio.models.JoinIntercambioModel;
import org.example.proyecto_intercambio.Intercambio.models.UserIntercambioModel;

import java.util.List;

public interface IntercambioService {
    public IntercambioModel crearIntercambio(IntercambioModel intercambio);
    public void buscarporIdInvitacion(JoinIntercambioModel joinIntercambio);
    public IntercambioModel readIntercambio(Long id);
    public void eliminarIntercambio(Long id);
    public List<UserIntercambioModel> readIntercambiosByUser(Long id);
}
