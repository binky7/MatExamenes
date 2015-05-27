/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package vista.interfaz;

import java.util.List;
import modelo.dto.ReactivoDTO;

/**
 * Interface de comunicación para enviar mensajes hacia la vista padre del
 * frame agregar reactivos para enviar los reactivos agregados
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public interface InterfaceExamen {
    
    /**
     * Método para obtener el padre de la vista que implementa la interface
     * 
     * @return una interface InterfaceVista que representa el padre de la vista
     * que implemnta esta interface
     */
    InterfaceVista getPadre();
    
    /**
     * Método para mandar el mensaje a la interface para mostrar los reactivos
     * ingresados en la clave ingresada
     * 
     * @param reactivos lista de ReactivoDTO que se desea mostrar en la clave
     * ingresada
     * @param clave el número de clave respecto a la vista en el que se desea
     * mostrar los reactivos ingresados
     */
    void mostrarReactivos(List<ReactivoDTO> reactivos, int clave);
    
}