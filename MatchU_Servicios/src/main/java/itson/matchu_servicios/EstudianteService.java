package itson.matchu_servicios;

import dtos.EstudianteDTO;
import dtos.LoginDTO;
import interfaces.IEstudianteService;
import itson.daos.EstudianteDAO;
import itson.daos.MatchDAO;
import itson.matchu_dominio.models.Estudiante;
import itson.matchu_dominio.models.Match;
import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ricardo
 */
public class EstudianteService implements IEstudianteService{
    
    private final EstudianteDAO estudianteDAO;
    private final MatchDAO matchDAO;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
        this.matchDAO = new MatchDAO();
    }
  
    @Override
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
    
    @Override
    public Estudiante registrarEstudiante(String nombre,String apellidos,String correo,String carrera,Integer semestre,String intereses,String password) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio.");
        }
        if (apellidos == null || apellidos.trim().isEmpty()) {
            throw new Exception("Los apellidos son obligatorios.");
        }
        if (correo == null || correo.trim().isEmpty()) {
            throw new Exception("El correo es obligatorio.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("La contraseña es obligatoria.");
        }
        if (estudianteDAO.existeCorreo(correo)) {
            throw new Exception("El correo ingresado ya está registrado.");
        }
        
        
        
        //Encriptar contraseña
        if (!password.startsWith("$2a$")) {
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());
            password = hash;
        }

        return estudianteDAO.guardar(nombre, apellidos, correo, carrera, semestre, intereses, password);
    }
    
    @Override
    public List<Estudiante> listarPendientesDeEvaluar(Long idEmisor) {
        return estudianteDAO.listarPendientesDeEvaluar(idEmisor);
    }
    
    @Override
    public Optional<Estudiante> buscarPorId(Long id) {
        return estudianteDAO.buscarPorId(id);
    }
    
    @Override
    public List<Match> listarMatchesDeEstudiante(Long idEstudiante) {
        return matchDAO.listarMatchesDeEstudiante(idEstudiante);
    }
    
    @Override
    public EstudianteDTO login(EstudianteDTO dto) throws Exception{

        if (dto == null) {
            throw new Exception("Datos inválidos");
        }

        if (dto.getCorreo() == null || dto.getCorreo().trim().isEmpty()) {
            throw new Exception("Correo requerido");
        }

        if (dto.getContrasena() == null || dto.getContrasena().isEmpty()) {
            throw new Exception("Contraseña requerida");
        }

        Estudiante estudiante = estudianteDAO.buscarPorCorreo(dto.getCorreo())
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        EstudianteDTO estudianteDTO = new EstudianteDTO(estudiante.getIdEstudiante(), estudiante.getNombre(), estudiante.getApellidos(), estudiante.getCorreo(), estudiante.getCarrera(), estudiante.getSemestre(), estudiante.getIntereses(), estudiante.getContrasena(), estudiante.getHobbies(), true);
        
        
        if (!BCrypt.checkpw(dto.getContrasena(), estudiante.getContrasena())) {
            throw new Exception("Contraseña incorrecta");
        }

        
        if (!estudiante.isActivo()) {
            throw new Exception("Usuario inactivo");
        }

        return estudianteDTO;
    }
}
