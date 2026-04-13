package itson.interfaces;

import itson.matchu_dominio.models.Estudiante;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jalt2
 */
public interface IEstudianteDAO {
    Estudiante guardar(String nombre,String apellidos,String correo,String carrera,Integer semestre,String intereses,String password);
    Optional<Estudiante> buscarPorId(Long id);
    Optional<Estudiante> buscarPorCorreo(String correo);
    List<Estudiante> listarTodosExcepto(Long idExcluido);
    List<Estudiante> listarPendientesDeEvaluar(Long idEmisor);
    boolean existeCorreo(String correo);
    Estudiante actualizar(Estudiante estudiante);
    void eliminar(Long id);
}
