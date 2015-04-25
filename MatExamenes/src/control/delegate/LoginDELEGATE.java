/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.delegate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 *
 * @author Alf
 */
public class LoginDELEGATE {
    private UsuarioDTO usuario;
    
    /**
     * 
     * @param usuarioValidar
     * @return -1 si la validacion fue exitosa,
     * 1 si el password no coincide, y 0 si el usuario no existe.
     */
    public int validarCredenciales(UsuarioDTO usuarioValidar) {
        int error = -1;
        UsuarioDTO usuario = null;
        try {
            usuario = Enlace.getPersistencia().obtenerUsuario(usuarioValidar.getUsuario());
            if (usuario != null) {
                if (usuario.getPassword().compareTo(usuarioValidar.getPassword()) == 0) {
                    this.usuario = usuario;
                } else {
                    error = 1;
                }
            } else {
                error = 0;
            }

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return error;
    }
    
    public UsuarioDTO obtenerUsuarioValidado(){
        return this.usuario;
    }

}
