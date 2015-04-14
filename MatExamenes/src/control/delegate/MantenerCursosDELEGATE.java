/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.delegate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import remoteAccess.Enlace;
import remoteAccess.Persistencia;

/**
 *
 * @author ivan
 */
public class MantenerCursosDELEGATE {
    private Persistencia persistencia;
    
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemasSinAsignar = null;
        
        try {
            listaTemasSinAsignar = Enlace.getPersistencia()
                    .obtenerTemasSinAsignar();
        } catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
                
        return listaTemasSinAsignar;
    }
    
    public boolean verificarExistencia(CursoDTO curso) {
        boolean ok = false;
        try {
             ok = Enlace.getPersistencia().verificarExistencia(curso);
        } catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
    
    public Integer guardarCurso(CursoDTO curso) {
        Integer id = null;
        try {
            id = Enlace.getPersistencia().guardarEntidad(curso);
        } catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return id;
    }
}
