package org.example.proyecto_intercambio.config.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.proyecto_intercambio.Intercambio.models.IntercambioModel;
import org.example.proyecto_intercambio.Participantes.models.ParticipantesModel;
import org.example.proyecto_intercambio.Temas.models.TemaModel;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorreoService {

    private final JavaMailSender mailSender;

    public CorreoService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCorreo(IntercambioModel intercambio) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            // Obtener los correos de todos los participantes
            List<ParticipantesModel> participantes = intercambio.getParticipantes();
            if (participantes != null && !participantes.isEmpty()) {
                String[] correosParticipantes = new String[participantes.size()];
                for (int i = 0; i < participantes.size(); i++) {
                    correosParticipantes[i] = participantes.get(i).getEmail();
                }
                helper.setTo(correosParticipantes);
            } else {
                helper.setTo("josuenro@gmail.com");
            }
            helper.setSubject("Confirmación de Intercambio - Token y Detalles");
            StringBuilder mensajeHtml = new StringBuilder("<html><body>" +
                    "<h1>Bienvenido al Intercambio</h1>" +
                    "<p>Gracias por participar en nuestro intercambio. Aquí están los detalles de tu intercambio:</p>" +
                    "<p><strong>Token de invitación: " + intercambio.getIdInvitacion() + "</strong></p>" +
                    "<p><strong>Monto máximo: </strong>" + intercambio.getMontoMaximo() + " MXN</p>" +
                    "<p><strong>Fecha de expiración: </strong>" + intercambio.getFechaExpiracion() + "</p>");

            if (intercambio.getParticipantes() != null && !intercambio.getParticipantes().isEmpty()) {
                mensajeHtml.append("<h3>Participantes:</h3><ul>");
                for (ParticipantesModel participante : intercambio.getParticipantes()) {
                    mensajeHtml.append("<li>").append(participante.getName()).append(" - ").append(participante.getEmail()).append("</li>");
                }
                mensajeHtml.append("</ul>");
            }
            if (intercambio.getTemas() != null && !intercambio.getTemas().isEmpty()) {
                mensajeHtml.append("<h3>Temas del intercambio:</h3><ul>");
                for (TemaModel tema : intercambio.getTemas()) {
                    mensajeHtml.append("<li>").append(tema.getNombreTema()).append("</li>");
                }
                mensajeHtml.append("</ul>");
            }
            mensajeHtml.append("<p>¡Esperamos que disfrutes de este intercambio!<br>Saludos cordiales,<br>Equipo de Intercambio</p></body></html>");
            helper.setText(mensajeHtml.toString(), true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

