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
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 *
 * @author Alf
 */
public class ContestarExamenDELEGATE {
    
    public List<ExamenAsignadoDTO> obtenerExamensAsignados(UsuarioDTO alumno){
        List<ExamenAsignadoDTO> ea = null;
        try {
            ea = Enlace.getPersistencia().obtenerExamenesAsignados(alumno);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ContestarExamenDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ea;
    }
    
    public ExamenAsignadoDTO obetnerExamenAsignado(ExamenAsignadoPK id){
        ExamenAsignadoDTO examen = null;
        try {
            examen = Enlace.getPersistencia().obtenerExamenAsignado(id);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ContestarExamenDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examen;
    }
    
    public boolean actualizarExamen(ExamenAsignadoDTO examen){
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().modificarEntidad(examen);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ContestarExamenDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    
}
