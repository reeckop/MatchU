/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.interfacesBO;

import itson.matchu_dominio.models.Estudiante;


/**
 *
 * @author jalt2
 */
public interface IEstudianteService {
    public Estudiante login(String correo, String contrasena) throws Exception;
    
    public Estudiante registrarEstudiante(Estudiante estudiante) throws Exception;
    
      
    public Estudiante actualizarEstudiante(Estudiante estudiante) throws Exception;
}
