/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.AsignarExamenesDELEGATE;
import java.util.List;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author ivan
 */
public class CVAsignarExamenes {

    private final AsignarExamenesDELEGATE asignarExamenesDELEGATE;
    private List<CursoDTO> listaCursos;
    private CursoDTO cursoSeleccionado;
    private List<ExamenDTO> listaExamenes;
    private ExamenDTO examenSeleccionado;
    private List<GrupoDTO> listaGrupos;
    private List<UsuarioDTO> listaAlumnos;

    public CVAsignarExamenes() {
        asignarExamenesDELEGATE = new AsignarExamenesDELEGATE();
    }

    public List<CursoDTO> obtenerCursos(UsuarioDTO usuarioActual) {
        List<CursoDTO> listaCursosBusqueda = null;

        if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin) {
            listaCursosBusqueda = asignarExamenesDELEGATE.obtenerCursos();
        } else if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro) {
            listaCursosBusqueda = asignarExamenesDELEGATE.obtenerCursosDeGrupo(usuarioActual);
        }

        listaCursos = listaCursosBusqueda;
        return listaCursosBusqueda;
    }

    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso,
            UsuarioDTO usuarioActual) {
        List<ExamenDTO> listaExamenesBusqueda = null;
        boolean todos = true;

        if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin) {
            todos = true;
        } else if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro) {
            todos = false;
        }

        listaExamenesBusqueda = asignarExamenesDELEGATE.obtenerExamenesPorCurso(curso,
                todos, usuarioActual);
        listaExamenes = listaExamenesBusqueda;

        return listaExamenesBusqueda;
    }

    public CursoDTO obtenerCurso(int indexCurso) {
        CursoDTO objCurso = null;

        if (listaCursos != null || !listaCursos.isEmpty()) {
            objCurso = listaCursos.get(indexCurso);
            cursoSeleccionado = objCurso;
        }

        return objCurso;
    }

    public ExamenDTO obtenerExamen(int indexExamen) {
        ExamenDTO objExamen = null;

        if (listaExamenes != null || !listaExamenes.isEmpty()) {
            objExamen = asignarExamenesDELEGATE
                    .obtenerExamen(listaExamenes.get(indexExamen).getId());

            examenSeleccionado = objExamen;
        }

        return objExamen;
    }

    public List<GrupoDTO> obtenerGruposPorCurso(UsuarioDTO usuarioActual) {
        if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin) {
            usuarioActual = null;
        }

        List<GrupoDTO> listaGruposBusqueda = asignarExamenesDELEGATE
                .obtenerGruposPorCurso(cursoSeleccionado, usuarioActual);
        listaGrupos = listaGruposBusqueda;

        return listaGruposBusqueda;
    }

    public List<UsuarioDTO> obtenerAlumnosDeGrupo(int indexGrupo) {
        List<UsuarioDTO> listaAlumnosBusqueda = null;

        listaAlumnosBusqueda = asignarExamenesDELEGATE
                .obtenerAlumnosDeGrupo(listaGrupos.get(indexGrupo));
        
        if(listaAlumnosBusqueda.isEmpty()) {
            listaAlumnosBusqueda = null;
        }
        
        listaAlumnos = listaAlumnosBusqueda;
        
        

        return listaAlumnosBusqueda;
    }

    public UsuarioDTO obtenerAlumno(String usuario) {
        UsuarioDTO alumno = null;

        for (UsuarioDTO alumnoBusqueda : listaAlumnos) {
            if (alumnoBusqueda.getUsuario().compareToIgnoreCase(usuario) == 0) {
                alumno = alumnoBusqueda;
            }
        }

        return alumno;
    }

    public ExamenDTO getExamenSeleccionado() {
        return this.examenSeleccionado;
    }

    public boolean asignarExamenes(List<ExamenAsignadoDTO> listaExamenesAsignados) {
        boolean ok = false;
        ok = asignarExamenesDELEGATE.asignarExamenes(listaExamenesAsignados);

        return ok;
    }
    
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK claveExamenPK) {
        ClaveExamenDTO claveExamen = null;
        
        claveExamen = asignarExamenesDELEGATE.obtenerClaveExamen(claveExamenPK);
        
        return claveExamen;
    }
}
