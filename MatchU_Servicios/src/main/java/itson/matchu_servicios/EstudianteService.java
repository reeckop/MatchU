package itson.matchu_servicios;

import itson.daos.EstudianteDAO;
import itson.interfacesBO.IEstudianteService;
import itson.matchu_dominio.models.Estudiante;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ricardo
 */
public class EstudianteService implements IEstudianteService{
    
    private final EstudianteDAO estudianteDAO;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
    }
  
    @Override
    public Estudiante login(String correo, String contrasena) throws Exception {
        Optional<Estudiante> estudianteOpt = estudianteDAO.buscarPorCorreo(correo);
        
        Estudiante estudiante = estudianteOpt.get();
        
        if (estudianteOpt.isEmpty()) {
            throw new Exception("El correo electrónico no está registrado.");
        }

        if ( estudiante == null || estudiante.getCorreo().trim().isEmpty()) {
            throw new Exception("Correo requerido");
        }

        if (estudiante.getContrasena() == null || estudiante.getContrasena().isEmpty()) {
            throw new Exception("Contraseña requerida");
        }
        
        boolean coincide = BCrypt.checkpw(
            contrasena,          
            estudiante.getContrasena()    
        );

        if (!coincide) {
            throw new Exception("Contraseña incorrecta");
        }

        
        if (!estudiante.isActivo()) {
            throw new Exception("Usuario inactivo");
        }

        
        return estudiante;
    }
    
    @Override
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
        
        //Encriptar contraseña
        if (!estudiante.getContrasena().startsWith("$2a$")) {
            String hash = BCrypt.hashpw(estudiante.getContrasena(), BCrypt.gensalt());
            estudiante.setContrasena(hash);
        }

        return estudianteDAO.guardar(estudiante);
    }
    
    //AGREGUE ACTUALIZAR -- kamila 
    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) throws Exception {
    if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
        throw new Exception("El nombre es obligatorio.");
    }
    if (estudiante.getCorreo() == null || estudiante.getCorreo().trim().isEmpty()) {
        throw new Exception("El correo es obligatorio.");
    }
    return estudianteDAO.actualizar(estudiante);
    }
}