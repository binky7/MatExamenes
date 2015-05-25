/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package vista.interfaz;

import modelo.dto.UsuarioDTO;


/**
 * Interface de comunicación para enviar mensajes entre las vistas de cada caso
 * de uso y el JFrame Principal
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public interface InterfaceVista {
    
    /**
     * Enumeración necesaria para identificar a los paneles en el JFrame
     * Principal
     */
    enum Vista {
        HOME, RegistrarTema, ConsultarTemas, ModificarTema,
        RegistrarCurso, ConsultarCursos, ModificarCurso, RegistrarGrupo,
        ConsultarGrupo, ModificarGrupo, RegistrarReactivo, ConsultarReactivos,
        ModificarReactivo, GenerarExamen, ConsultarExamenes, ModificarExamen,
        AsignarExamenes, ConsultarCalificaciones, ConsultarCalificacionesExamen,
        RegistrarUsuario,ConsultarUsuarios,ModificarUsuario, GenerarEstadisticas,
        ContestarExamen,BuscarExamenAsignado
    }
    
    /**
     * Se utiliza como intermediario para enviarle el objeto obtenido de una
     * vista a otra a través del JFrame Principal
     * 
     * @param entidad el objeto dto que se mostrará en la vista destino
     * @param vista el identificador de la vista destino que se mostrará y donde
     * se enviará la entidad.
     */
    void mostrarVistaConEntidad(Object entidad, Vista vista);
    
    /**
     * Se utiliza para ordenarle al JFrame Principal el mostrar la vista
     * correspondiente al identificador enviado.
     * 
     * @param vista el identificador de la vista que se quiere mostrar
     */
    void mostrarVista(Vista vista);
    
    /**
     * Este método es usado para recibir el objeto entidad y mostrar sus datos
     * en la vista que implementa esta interface
     * 
     * @param entidad el objeto dto que se mostrara en la vista
     */
    void mostrarEntidad(Object entidad);

    /**
     * Este método le muestra un mensaje de confirmación al
     * usuario para antes de cambiar de vista en el JFrame Principal
     * 
     * @return true si el usuario ha aceptado el cambio de vista, falso si ocurre
     * lo contrario
     */
    boolean confirmarCambio();
    
    /**
     * Este método sirve para obtener el objeto usuarioActual que representa al
     * usuario que inició sesión en el sistema. Es obtenido del JFrame Principal
     * 
     * @return un objeto UsuarioDTO que representa al usuario que está ingresado
     * en el sistema actualmente
     */
    UsuarioDTO obtenerUsuarioActual();
    
    /**
     * Este método se encarga de limpiar los componentes de la vista que implemente
     * la interfaz. Además también libera la memoria de sus respectivos
     * controladores de la vista
     */
    void limpiar();
}