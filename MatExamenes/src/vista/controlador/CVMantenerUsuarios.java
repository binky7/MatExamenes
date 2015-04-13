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
    
    
    public CVMantenerUsuarios(){
        mantenerUsuariosDELEGATE = new MantenerUsuariosDELEGATE();
    }
    
    public Integer guardarUsuario(UsuarioDTO usuario){
        Integer id = mantenerUsuariosDELEGATE.guardarUsuario(usuario);        
        return id;
    }
    
    public List<UsuarioDTO> obtenerUsuariosPorApellido(String apellido){
        List<UsuarioDTO> usuarios;
        usuarios = mantenerUsuariosDELEGATE.obtenerUsuariosPorApellido(apellido);
        return usuarios;
    }
}
