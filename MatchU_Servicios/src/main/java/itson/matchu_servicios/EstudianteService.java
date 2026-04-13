package itson.matchu_servicios;

import itson.daos.EstudianteDAO;
import itson.daos.MatchDAO;
import itson.matchu_dominio.models.Estudiante;
import itson.matchu_dominio.models.Match;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Ricardo
 */
public class EstudianteService {
    
    private final EstudianteDAO estudianteDAO;
    private final MatchDAO matchDAO;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
        this.matchDAO = new MatchDAO();
    }
  
    public Estudiante login(String correo, String contrasena) throws Exception {
        Optional<Estudiante> estudianteOpt = estudianteDAO.buscarPorCorreo(correo);

        if (estudianteOpt.isEmpty()) {
            throw new Exception("El correo electrónico no está registrado.");
        }

        Estudiante estudiante = estudianteOpt.get();

        if (!estudiante.getContrasena().equals(contrasena)) {
            throw new Exception("Contraseña incorrecta.");
        }

        return estudiante;
    }
    
    public Estudiante registrarEstudiante(Estudiante estudiante) throws Exception {
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio.");
        }
        if (estudiante.getApellidos() == null || estudiante.getApellidos().trim().isEmpty()) {
            throw new Exception("Los apellidos son obligatorios.");
        }
        if (estudiante.getCorreo() == null || estudiante.getCorreo().trim().isEmpty()) {
            throw new Exception("El correo es obligatorio.");
        }
        if (estudiante.getContrasena() == null || estudiante.getContrasena().trim().isEmpty()) {
            throw new Exception("La contraseña es obligatoria.");
        }
        if (estudianteDAO.existeCorreo(estudiante.getCorreo())) {
            throw new Exception("El correo ingresado ya está registrado.");
        }

        return estudianteDAO.guardar(estudiante);
    }

    public List<Estudiante> listarPendientesDeEvaluar(Long idEmisor) {
        return estudianteDAO.listarPendientesDeEvaluar(idEmisor);
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteDAO.buscarPorId(id);
    }

    public List<Match> listarMatchesDeEstudiante(Long idEstudiante) {
        return matchDAO.listarMatchesDeEstudiante(idEstudiante);
    }
}
