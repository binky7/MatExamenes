/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.interfaz;

import java.util.List;
import modelo.dto.ReactivoDTO;

/**
 *
 * @author Jesus Donaldo
 */
public interface InterfaceExamen {
    
    InterfaceVista getPadre();
    
    void mostrarReactivos(List<ReactivoDTO> reactivos, int clave);
    
}
