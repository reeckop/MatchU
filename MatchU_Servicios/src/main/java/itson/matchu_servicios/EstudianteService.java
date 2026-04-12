package itson.matchu_servicios;

import itson.daos.EstudianteDAO;
import itson.matchu_dominio.models.Estudiante;

/**
 *
 * @author Ricardo
 */
public class EstudianteService {
    
    private final EstudianteDAO estudianteDAO;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) throws Exception {
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio.");
        }
        if (estudiante.getCorreo() == null || estudiante.getCorreo().trim().isEmpty()) {
            throw new Exception("El correo es obligatorio.");
        }
        
        if (estudianteDAO.existeCorreo(estudiante.getCorreo())) {
            throw new Exception("El correo ingresado ya está registrado.");
        }

        return estudianteDAO.guardar(estudiante);
    }
}