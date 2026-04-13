/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.controllers;


import dtos.EstudianteDTO;
import interfaces.IEstudianteService;

import itson.matchu_servicios.EstudianteService;



/**
 *
 * @author jalt2
 */
public class ControlEstudiante {
    IEstudianteService estudianteService;

    public ControlEstudiante() {
        estudianteService = new EstudianteService();
    }
    
    public boolean registrar(String nombre,String apellidos,String correo,String carrera,Integer semestre,String intereses,String password) throws Exception{
        
        try {
            estudianteService.registrarEstudiante(nombre, apellidos, correo, carrera, semestre, intereses, password);

            return true;
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            return false;
        }
         
    }
    
    public EstudianteDTO login(EstudianteDTO dto) throws Exception {
        return estudianteService.login(dto);
    }
    
    
}
