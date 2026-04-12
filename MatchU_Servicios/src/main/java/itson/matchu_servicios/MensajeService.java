package itson.matchu_servicios;

import itson.daos.MatchDAO;
import itson.daos.MensajeDAO;
import itson.matchu_dominio.models.Match;
import itson.matchu_dominio.models.Mensaje;
import itson.matchu_dominio.models.Estudiante;
import java.util.Optional;

/**
 *
 * @author Ricardo
 */
public class MensajeService {
    
    private final MensajeDAO mensajeDAO;
    private final MatchDAO matchDAO;

    public MensajeService() {
        this.mensajeDAO = new MensajeDAO();
        this.matchDAO = new MatchDAO();
    }

    public Mensaje enviarMensaje(Long idMatch, Estudiante remitente, String contenido) throws Exception {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new Exception("El contenido del mensaje no puede estar vacío.");
        }

        Optional<Match> matchOpt = matchDAO.buscarPorId(idMatch);
        if (matchOpt.isEmpty() || !matchOpt.get().isActivo()) {
            throw new Exception("No se puede enviar el mensaje: El Match no existe o está inactivo.");
        }

        Match match = matchOpt.get();
        
        if (match.getEstudiante1().getIdEstudiante() != remitente.getIdEstudiante() && 
            match.getEstudiante2().getIdEstudiante() != remitente.getIdEstudiante()) {
            throw new Exception("No eres parte de este Match.");
        }

        Mensaje mensaje = new Mensaje(match, remitente, contenido);
        return mensajeDAO.guardar(mensaje);
    }
}