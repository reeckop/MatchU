package itson.matchu_servicios;

import itson.daos.InteraccionDAO;
import itson.daos.MatchDAO;
import itson.daos.EstudianteDAO;
import itson.matchu_dominio.models.Estudiante;
import itson.matchu_dominio.models.Interaccion;
import itson.matchu_dominio.models.Match;
import java.util.Optional;

/**
 *
 * @author Ricardo
 */
public class InteraccionService {
    
    private final InteraccionDAO interaccionDAO;
    private final MatchDAO matchDAO;
    private final EstudianteDAO estudianteDAO;

    public InteraccionService() {
        this.interaccionDAO = new InteraccionDAO();
        this.matchDAO = new MatchDAO();
        this.estudianteDAO = new EstudianteDAO();
    }

    public void registrarLike(Long idEmisor, Long idReceptor) throws Exception {
        if (idEmisor.equals(idReceptor)) {
            throw new Exception("No puedes darte 'Me gusta' a ti mismo.");
        }

        Optional<Estudiante> emisorOpt = estudianteDAO.buscarPorId(idEmisor);
        Optional<Estudiante> receptorOpt = estudianteDAO.buscarPorId(idReceptor);

        if (emisorOpt.isEmpty() || receptorOpt.isEmpty()) {
            throw new Exception("Uno de los estudiantes no existe.");
        }

        // Verificar si ya existe la interacción para no duplicar datos
        if (interaccionDAO.buscar(idEmisor, idReceptor).isPresent()) {
            throw new Exception("Ya has interactuado con este perfil.");
        }

        // 1. Guardar el "Me Gusta"
        Interaccion interaccion = new Interaccion(emisorOpt.get(), receptorOpt.get(), Interaccion.TipoInteraccion.LIKE);
        interaccionDAO.guardar(interaccion);

        // 2. LÓGICA DE MATCH: Verificar si el receptor ya le había dado "Me Gusta" al emisor
        if (interaccionDAO.dioCLikeA(idReceptor, idEmisor)) {
            // ¡Es un Match!
            Match nuevoMatch = new Match(emisorOpt.get(), receptorOpt.get());
            matchDAO.guardar(nuevoMatch);
            System.out.println("¡MATCH CREADO entre " + emisorOpt.get().getNombre() + " y " + receptorOpt.get().getNombre() + "!");
        }
    }
}