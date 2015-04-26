/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.delegate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 *
 * @author Alf
 */
public class MantenerUsuariosDELEGATE {

    public Integer guardarUsuario(UsuarioDTO usuario) {
        Integer id = null;

        try {
            id = Enlace.getPersistencia().guardarEntidad(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return id;
    }

    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido) {
        List<UsuarioDTO> usuarios = null;
        try {
            usuarios = Enlace.getPersistencia().obtenerUsuariosPorApellido(apellido);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return usuarios;
    }

    public boolean modificarUsuario(UsuarioDTO usuario) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().modificarEntidad(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }

    public boolean eliminarUsuario(UsuarioDTO usuario) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().eliminarEntidad(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return ok;
    }

    public UsuarioDTO obtenerUsuario(String usuario) {
        UsuarioDTO _usuario = null;
        try {
            _usuario = Enlace.getPersistencia().obtenerUsuario(usuario);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return _usuario;
    }

}
