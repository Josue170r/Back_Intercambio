package org.example.proyecto_intercambio.Intercambio.service.impl;

import org.example.proyecto_intercambio.Intercambio.models.IntercambioModel;
import org.example.proyecto_intercambio.Intercambio.models.JoinIntercambioModel;
import org.example.proyecto_intercambio.Intercambio.models.UserIntercambioModel;
import org.example.proyecto_intercambio.Intercambio.repository.IntercambioRepository;
import org.example.proyecto_intercambio.Intercambio.repository.UsuarioIntercambioRepository;
import org.example.proyecto_intercambio.Intercambio.service.IntercambioService;
import org.example.proyecto_intercambio.Participantes.models.ParticipantesModel;
import org.example.proyecto_intercambio.Temas.models.TemaModel;
import org.example.proyecto_intercambio.User.models.Usuario;
import org.example.proyecto_intercambio.User.repository.UserRepository;
import org.example.proyecto_intercambio.config.mail.CorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class IntercambioSerImpl implements IntercambioService {
    @Autowired
    private IntercambioRepository intercambioRepository;
    @Autowired
    private CorreoService correoService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsuarioIntercambioRepository userIntercambiorep;

    @Override
    public IntercambioModel crearIntercambio(IntercambioModel intercambio) {
        Long idUsuario = intercambio.getCreatedBy().getIdUsuario();
        Usuario usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Date fechaExpiracion = intercambio.getFechaExpiracion();
        if (fechaExpiracion == null) {
            throw new IllegalArgumentException("La fecha de expiración no puede ser nula");
        } else if (fechaExpiracion.before(new Date())) {
            throw new IllegalArgumentException("La fecha de expiración debe ser en el futuro");
        }
        SecureRandom random = new SecureRandom();
        int token = random.nextInt(900000) + 100000;
        intercambio.setIdInvitacion(String.valueOf(token));

        if (intercambio.getParticipantes() != null) {
            for (ParticipantesModel participante : intercambio.getParticipantes()) {
                participante.setIntercambio(intercambio);
            }
        }

        if (intercambio.getTemas() != null) {
            for (TemaModel tema : intercambio.getTemas()) {
                tema.setIntercambio(intercambio);
            }
        }
        correoService.enviarCorreo(intercambio);
        intercambio.setCreatedBy(usuario);
        return intercambioRepository.save(intercambio);
    }

    @Override
    public void buscarporIdInvitacion(JoinIntercambioModel joinIntercambio) {
        Long idUsuario = joinIntercambio.getIdUsuario();
        Usuario usuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        IntercambioModel intercambio = intercambioRepository.findByIdInvitacion(joinIntercambio.getIdInvitacion());
        if (intercambio == null) {
            throw new IllegalArgumentException(
                "No se encontró un intercambio con el ID de invitación proporcionado: " + joinIntercambio.getIdInvitacion()
            );
        }

        List<ParticipantesModel> participantes = intercambio.getParticipantes();
        if (participantes != null) {
            participantes.removeIf(participante -> participante.getEmail().equals(usuario.getEmail()));
        }
        Random random = new Random();
        ParticipantesModel participanteAsignado = participantes.get(random.nextInt(participantes.size()));
        System.out.println(participanteAsignado.toString());
        UserIntercambioModel userIntercambioModel = new UserIntercambioModel();
        userIntercambioModel.setIntercambio(intercambio);
        userIntercambioModel.setUsuario(usuario);
        userIntercambioModel.setParticipante(participanteAsignado);
        userIntercambiorep.save(userIntercambioModel);
    }

    @Override
    public IntercambioModel readIntercambio(Long id) {
        return intercambioRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Intercambio con id" + id + " no encontrado")
        );
    }

    @Override
    public void eliminarIntercambio(Long id) {
        intercambioRepository.deleteById(id);
    }

    @Override
    public List<UserIntercambioModel> readIntercambiosByUser(Long id) {
        Usuario usuario = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return userIntercambiorep.findByUsuario(usuario);
    }
}
