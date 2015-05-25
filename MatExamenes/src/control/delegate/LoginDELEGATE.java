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
package control.delegate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 * Esta clase se encarga de enviar las peticiones del control vista del caso de
 * uso de Login a la interface Persistencia, la cuál oculta la forma en la cuál
 * se tiene acceso a capas inferiores. El Delegate se encarga de delegar el
 * trabajo a esta interface y obtener los datos que se le pidan.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class LoginDELEGATE {

    /**
     * Almacenara el usuario, si sus credenciales fueron correctas.
     */
    private UsuarioDTO usuario;

    /**
     * Valida que el usuario exista en la base de datos y su password fue el
     * correcto.
     *
     * @param usuarioValidar
     * @return -1 si la validacion fue exitosa, 1 si el password no coincide, y
     * 0 si el usuario no existe.
     */
    public int validarCredenciales(UsuarioDTO usuarioValidar) {
        int error = -1;
        UsuarioDTO unUsuario;
        try {
            unUsuario = Enlace.getPersistencia().obtenerUsuario(usuarioValidar.getUsuario());
            if (unUsuario != null) {
                if (unUsuario.getPassword().compareTo(usuarioValidar.getPassword()) == 0) {
                    this.usuario = unUsuario;
                } else {
                    error = 1;
                }
            } else {
                error = 0;
            }

        } catch (RemoteException | NotBoundException ex) {
        }

        return error;
    }

    /**
     * Obtiene el usuario que fue previamente validado.
     *
     * @return El usuario previamente validado.
     */
    public UsuarioDTO getUsuarioValidado() {
        return this.usuario;
    }

}
