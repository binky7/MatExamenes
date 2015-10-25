/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández, Alfredo Rouse Madrigal
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
package control.delegate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;
import remoteAccess.Enlace;

/**
 * Esta clase se encarga de enviar las peticiones del control vista del caso de
 * uso de Mantener Usuarios a la interface Persistencia, la cuál oculta la forma
 * en la cuál se tiene acceso a capas inferiores. El Delegate se encarga de
 * delegar el trabajo a esta interface y obtener los datos que se le pidan.
 *
 * @author Fernando Enrique Avendaño Hernández, Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class MantenerUsuariosDELEGATE {

    /**
     * Guarda el usuario en la base de datos.
     *
     * @param usuario El usuario que se almacenara en la base de datos.
     * @return El id del nuevo usuario.<br>
     * null en caso de error en la inserción.
     */
    public Integer guardarUsuario(UsuarioDTO usuario) {
        Integer id = null;

        try {
            id = Enlace.getPersistencia().guardarEntidad(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

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
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerUsuariosPorNombreOApellidos(nombre);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return usuarios;
    }

    public List<UsuarioDTO> obtenerAlumnosPorApellido(String apellido) {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerAlumnosPorApellido(apellido);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return usuarios;
    }

    public List<UsuarioDTO> obtenerAlumnosPorApellidoM(String apellidoMaterno) {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerAlumnosPorApellidoM(apellidoMaterno);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return usuarios;
    }

    public List<UsuarioDTO> obtenerAlumnosPorNombre(String nombre) {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerAlumnosPorNombre(nombre);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return usuarios;
    }

    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido, Tipo tipo) {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerUsuariosPorApellido(apellido, tipo);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return usuarios;
    }

    public List<UsuarioDTO> obtenerUsuariosPorApellidoM(String apellidoMaterno, Tipo tipo) {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerUsuariosPorApellidoM(apellidoMaterno, tipo);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return usuarios;
    }

    public List<UsuarioDTO> obtenerUsuariosPorNombre(String nombre, Tipo tipo) {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerUsuariosPorNombre(nombre, tipo);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

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
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().modificarEntidad(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

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
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().eliminarEntidad(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return ok;
    }

    /**
     * Busca en la base de datos el usuario.
     *
     * @param unUsuario Usuario a consultar en la base de datos.
     * @return El usuario en caso de que exista en la base de datos. null de
     * otra forma.
     */
    public UsuarioDTO obtenerUsuario(String unUsuario) {
        UsuarioDTO usuario = null;
        try {
            usuario = Enlace.getPersistencia().obtenerUsuario(unUsuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return usuario;
    }

    /**
     * Validara si el usuario se encuentra inscrito en un grupo.
     *
     * @param usuario El usuario a validar si existe en un grupo.
     * @return Verdadero si el usuario esta inscrito a un grupo.<br>
     * Falso de otra forma.
     */
    public boolean perteneceAGrupo(UsuarioDTO usuario) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().perteneceAGrupo(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return ok;
    }
}
