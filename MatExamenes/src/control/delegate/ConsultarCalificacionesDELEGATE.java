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
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
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
            cursos = Enlace.getPersistencia().obtenerCursosDeGrupo(maestro);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso) {
        List<GrupoDTO> grupos = null;
        try {
            grupos = Enlace.getPersistencia().obtenerGruposPorCurso(curso, null);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupos;
    }

    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO usuarioActual) {
        List<GrupoDTO> grupos = null;
        try {
            grupos = Enlace.getPersistencia().obtenerGruposPorCurso(curso, usuarioActual);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupos;
    }

    public List<UsuarioDTO> obtenerAlumnosDeGrupo(GrupoDTO grupo) {
        List<UsuarioDTO> alumnos = null;
        try {
            alumnos = Enlace.getPersistencia().obtenerAlumnosDeGrupo(grupo);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    public List<ExamenAsignadoDTO> obtenerExamenes(UsuarioDTO alumno) {
        List<ExamenAsignadoDTO> examenes = null;
        try {
            examenes = Enlace.getPersistencia().obtenerExamenesAsignados(alumno);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examenes;
    }

    public ExamenAsignadoDTO obtenerExamen(ExamenAsignadoPK id) {
        ExamenAsignadoDTO examen = null;
        try {
            examen = Enlace.getPersistencia().obtenerExamenContestado(id);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examen;
    }
}
