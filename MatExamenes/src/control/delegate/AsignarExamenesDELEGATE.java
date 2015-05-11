/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.delegate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 *
 * @author ivan
 */
public class AsignarExamenesDELEGATE {

    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = null;

        try {
            listaCursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return listaCursos;
    }

    public List<CursoDTO> obtenerCursosDeGrupo(UsuarioDTO maestro) {
        List<CursoDTO> listaCursos = null;

        try {
            listaCursos = Enlace.getPersistencia().obtenerCursosDeGrupo(maestro);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return listaCursos;
    }

    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso,
            boolean todos, UsuarioDTO usuarioActual) {
        List<ExamenDTO> listaExamenes = null;

        try {
            listaExamenes = Enlace.getPersistencia()
                    .obtenerExamenesPorCurso(curso, todos, usuarioActual);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return listaExamenes;
    }

    public ExamenDTO obtenerExamen(int idExamen) {
        ExamenDTO examen  = null;
        try {
            examen = Enlace.getPersistencia()
                    .obtenerExamenIncompleto(idExamen);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examen;
    }

    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO usuarioActual) {
        List<GrupoDTO> listaGrupos = null;

        try {
            listaGrupos = Enlace.getPersistencia()
                    .obtenerGruposPorCurso(curso, usuarioActual);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return listaGrupos;
    }

    public List<UsuarioDTO> obtenerAlumnosDeGrupo(GrupoDTO grupo) {
        List<UsuarioDTO> listaAlumnos = null;

        try {
            listaAlumnos = Enlace.getPersistencia()
                    .obtenerAlumnosDeGrupo(grupo);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return listaAlumnos;
    }

    public boolean asignarExamenes(List<ExamenAsignadoDTO> listaExamenesAsignados) {
        boolean ok = false;

        try {
            ok = Enlace.getPersistencia()
                    .asignarExamenes(listaExamenesAsignados);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }
    
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK claveExamenPK) {
        ClaveExamenDTO claveExamen = null;
        
        try {
            claveExamen = Enlace.getPersistencia()
                    .obtenerClaveExamen(claveExamenPK);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return claveExamen;
    }
}
