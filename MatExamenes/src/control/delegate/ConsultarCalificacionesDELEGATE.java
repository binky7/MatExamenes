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
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 *
 * @author FernandoEnrique
 */
public class ConsultarCalificacionesDELEGATE {

    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> cursos = null;
        try {
            cursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    public List<CursoDTO> obtenerCursosPorMaestro(UsuarioDTO maestro) {
        List<CursoDTO> cursos = null;
        try {
            cursos = Enlace.getPersistencia().obtenerCursosPorMaestro(maestro);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso) {
        List<GrupoDTO> grupos = null;
        try {
            grupos = Enlace.getPersistencia().obtenerGrupos(curso);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupos;
    }

    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO usuarioActual) {
        List<GrupoDTO> grupos = null;
        try {
            grupos = Enlace.getPersistencia().obtenerGrupos(curso, usuarioActual);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupos;
    }
}
