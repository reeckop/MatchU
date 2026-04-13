/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.matchu_presentacion;

import dtos.EstudianteDTO;

/**
 *
 * @author jalt2
 */
public class Sesion {

    private static EstudianteDTO usuarioActual;

    public static void setUsuario(EstudianteDTO usuario) {
        usuarioActual = usuario;
    }

    public static EstudianteDTO getUsuario() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static boolean haySesion() {
        return usuarioActual != null;
    }
}
