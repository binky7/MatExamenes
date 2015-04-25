/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.LoginDELEGATE;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Alf
 */
public class CVLogin {
    private LoginDELEGATE loginDELEGATE;
    private UsuarioDTO usuario;
    
    
    public CVLogin(){
        loginDELEGATE = new LoginDELEGATE();
    }
    /**
     * 
     * @param usuarioValidar
     * @return -1 si la validacion fue exitosa,
     * 1 si el password no coincide, y 0 si el usuario no existe.
     */
    public int validarCredenciales(UsuarioDTO usuarioValidar){
        int error;
        error = loginDELEGATE.validarCredenciales(usuarioValidar);
        if(error == -1){
            usuario = loginDELEGATE.obtenerUsuarioValidado();
        }
        
        return error;
    }
    
    public UsuarioDTO obtenerUsuarioValidado(){
        return this.usuario;
    }
}
