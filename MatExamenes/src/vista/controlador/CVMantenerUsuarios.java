/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
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
package vista.controlador;

import control.delegate.MantenerUsuariosDELEGATE;
import java.util.List;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class CVMantenerUsuarios {

    private final MantenerUsuariosDELEGATE mantenerUsuariosDELEGATE;
    /**
     * Lista de usuarios usada en ConsultarUsuarios resulatante de la busqueda.
     */
    private List<UsuarioDTO> usuarios;
    /**
     * Usuario a ser modificado.
     */
    private UsuarioDTO usuarioAModificar;

    /**
     * Crea un objeto CVMantenerUsuarios e inicializa los atributos.
     */
    public CVMantenerUsuarios() {
        mantenerUsuariosDELEGATE = new MantenerUsuariosDELEGATE();
        usuarios = null;
    }

    /**
     * Guarda el usuario en la base de datos.
     *
     * @param usuario El usuario que se almacenara en la base de datos.
     * @return El id del nuevo usuario.<br>
     * null en caso de error en la inserción.
     */
    public Integer guardarUsuario(UsuarioDTO usuario) {
        Integer id = mantenerUsuariosDELEGATE.guardarUsuario(usuario);
        return id;
    }

    /**
     * Retorna una lista de usuarios, seran buscados en base al parametro en
     * nombre, apellido paterno, apellido materno, devolviendo solo el la primer
     * coincidencia.
     *
     * @param nombre Puede ser un nombre o apellidos, por el cual se desea
     * buscar.
     * @return Una Lista de los usuarios que coincidan con el parámetro.
     */
    public List<UsuarioDTO> obtenerUsuariosPorNombreOApellidos(String nombre) {
        usuarios = mantenerUsuariosDELEGATE.obtenerUsuariosPorNombreOApellidos(nombre);
        return usuarios;
    }

    /**
     * Modifica el usuario en la base de datos.
     *
     * @param usuario El usuario a ser modificado.
     * @return Verdadero si la modificación fue exitosa.<br>
     * Falso de otra forma.
     */
    public boolean modificarUsuario(UsuarioDTO usuario) {
        boolean ok;
        ok = mantenerUsuariosDELEGATE.modificarUsuario(usuario);
        return ok;
    }

    /**
     * Elimina al usuario de la base de datos.
     *
     * @param usuario El usuario a ser eliminado.
     * @return Verdadero si la eliminación fue exitosa.<br>
     * Falso de otra forma.
     */
    public boolean eliminarUsuario(UsuarioDTO usuario) {
        boolean ok;
        ok = mantenerUsuariosDELEGATE.eliminarUsuario(usuario);
        return ok;
    }

    /**
     * Retorna la lista de usuarios previamente buscada.
     *
     * @return List de usuarios.
     */
    public List<UsuarioDTO> getUsuariosBuscados() {
        return usuarios;
    }

    /**
     * valida si el usuario esta registrado en la base de datos.
     *
     * @param usuario
     * @return retorna verdadero si el usuario no exite.<br>
     * Falso de otra forma.
     */
    public boolean validarUsuario(String usuario) {
        boolean ok = false;
        if (mantenerUsuariosDELEGATE.obtenerUsuario(usuario) == null) {
            ok = true;
        }
        return ok;
    }

    public void setUsuarioModificar(UsuarioDTO usuario) {
        this.usuarioAModificar = usuario;
    }
    
    public UsuarioDTO getUsuarioModificar(){
        return this.usuarioAModificar;
    }

}
