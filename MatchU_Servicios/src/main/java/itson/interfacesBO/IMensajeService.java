/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package itson.interfacesBO;

import itson.matchu_dominio.models.Estudiante;
import itson.matchu_dominio.models.Mensaje;

/**
 *
 * @author jalt2
 */
public interface IMensajeService {
    public Mensaje enviarMensaje(Long idMatch, Estudiante remitente, String contenido) throws Exception;
}
