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
import modelo.dto.GrupoDTO;
import remoteAccess.Enlace;
import remoteAccess.Persistencia;

/**
 *
 * @author FernandoEnrique
 */
public class MantenerGruposDELEGATE {

    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = null;
        try {
            listaCursos = Enlace.getPersistencia()
                    .obtenerEntidades(CursoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return listaCursos;
    }

    public int guardarGrupo(GrupoDTO grupo) {
        int id = -1;
        try {
            id = Enlace.getPersistencia().guardarEntidad(grupo);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public List<GrupoDTO> obtenerGrupos() {
        List<GrupoDTO> listaGrupos = null;
        try {
            listaGrupos = Enlace.getPersistencia().obtenerEntidades(GrupoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGrupos;
    }

    public GrupoDTO obtenerGrupo(int id) {
        GrupoDTO grupo = null;       
        try {
            grupo = Enlace.getPersistencia().obtenerGrupo(id);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupo;
    }

    public boolean modificarGrupo(GrupoDTO grupo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean eliminarGrupo(GrupoDTO objGrupo) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().eliminarEntidad(objGrupo);

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

}
