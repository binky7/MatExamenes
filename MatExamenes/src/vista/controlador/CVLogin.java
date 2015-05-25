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

import control.delegate.LoginDELEGATE;
import modelo.dto.UsuarioDTO;

/**
 * Esta clase se encarga de enviar las peticiones de las vistas del caso de uso
 * de Login al delegate del mismo caso de uso, en el cuál se tiene acceso a
 * capas inferiores para acceder a la base de datos. También mantiene en memoria
 * los objetos dto que se utilizan para el caso de uso.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class CVLogin {

    /**
     * Objeto delegate del caso de uso Mantener Usuarios. Delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación
     */
    private final LoginDELEGATE loginDELEGATE;
    /**
     * Objeto UsuarioDTO, se almacena el usuario cuando se validaron las
     * credenciales.
     */
    private UsuarioDTO usuario;

    /**
     * Crea un objeto CVLogin e inicializa sus atributos.
     */
    public CVLogin() {
        loginDELEGATE = new LoginDELEGATE();
    }

    /**
     * Valida las credenciales ingresadas por los usuarios.
     *
     * @param usuarioValidar
     * @return -1 si la validacion fue exitosa, 1 si el password no coincide, y
     * 0 si el usuario no existe.
     */
    public int validarCredenciales(UsuarioDTO usuarioValidar) {
        int error = loginDELEGATE.validarCredenciales(usuarioValidar);
        if (error == -1) {
            usuario = loginDELEGATE.getUsuarioValidado();
        }
        return error;
    }

    /**
     * Obtiene el usuario que fue previamente validado.
     *
     * @return El usuario previamente validado.
     */
    public UsuarioDTO getUsuarioValidado() {
        return usuario;
    }
}
