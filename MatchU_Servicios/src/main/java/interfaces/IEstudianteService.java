/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.EstudianteDTO;
import itson.matchu_dominio.models.Estudiante;
import itson.matchu_dominio.models.Match;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author jalt2
 */
public interface IEstudianteService {
    public Estudiante login(String correo, String contrasena) throws Exception;
    
    public Estudiante registrarEstudiante(String nombre,String apellidos,String correo,String carrera,Integer semestre,String intereses,String password) throws Exception;

    public List<Estudiante> listarPendientesDeEvaluar(Long idEmisor);

    public Optional<Estudiante> buscarPorId(Long id);

    public List<Match> listarMatchesDeEstudiante(Long idEstudiante);
    
    public EstudianteDTO login(EstudianteDTO dto)throws Exception;
}
