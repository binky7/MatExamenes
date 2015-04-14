/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerUsuariosDELEGATE;
import java.util.List;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Alf
 */
public class CVMantenerUsuarios {
    private final MantenerUsuariosDELEGATE mantenerUsuariosDELEGATE;
    private List<UsuarioDTO> usuarios;
    
    
    public CVMantenerUsuarios(){
        mantenerUsuariosDELEGATE = new MantenerUsuariosDELEGATE();
    }
    
    public Integer guardarUsuario(UsuarioDTO usuario){
        Integer id = mantenerUsuariosDELEGATE.guardarUsuario(usuario);        
        return id;
    }
    
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido){
        usuarios = mantenerUsuariosDELEGATE.obtenerUsuariosPorApellido(apellido);
        return usuarios;
    }
    
    public boolean modificarUsuario(UsuarioDTO usuario){
        boolean ok;
        ok = mantenerUsuariosDELEGATE.modificarUsuario(usuario);
        return ok;
    }
    
    public boolean eliminarUsuario(UsuarioDTO usuario){
        boolean ok;
        ok = mantenerUsuariosDELEGATE.eliminarUsuario(usuario);
        return ok;
    }
    
    public List<UsuarioDTO> obtenerUsuariosBuscados(){
        return usuarios;
    }

}
