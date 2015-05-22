/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.ConsultarCalificacionesDELEGATE;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;

/**
 *
 * @author FernandoEnrique
 */
public class CVConsultarCalificaciones {

    private ConsultarCalificacionesDELEGATE consultarCalificacionesDELEGATE;

    private UsuarioDTO usuarioActual;
    private List<CursoDTO> listaCursos;
    private CursoDTO curso;
    private List<GrupoDTO> listaGrupos;
    private GrupoDTO grupo;
    private List<UsuarioDTO> listaAlumnos;
    private UsuarioDTO alumno;
    private List<ExamenAsignadoDTO> listaExamenes;
    private ExamenAsignadoDTO examen;
    private List<ReactivoAsignadoDTO> listaReactivos;

    public CVConsultarCalificaciones() {
        consultarCalificacionesDELEGATE = new ConsultarCalificacionesDELEGATE();
    }

    public List<CursoDTO> obtenerCursos(UsuarioDTO actual) {
        listaCursos = null;
        usuarioActual = actual;
        if (actual.getTipo() == Tipo.Admin) {
            listaCursos = consultarCalificacionesDELEGATE.obtenerCursos();
        } else {
            listaCursos = consultarCalificacionesDELEGATE
                    .obtenerCursosPorMaestro(actual);
        }
        return listaCursos;
    }

    public List<GrupoDTO> obtenerGrupos(String nombreCurso) {
        listaGrupos = null;
        for (CursoDTO c : listaCursos) {
            if (c.getNombre().equalsIgnoreCase(nombreCurso)) {
                this.curso = c;
            }
        }
        if (usuarioActual.getTipo() == Tipo.Admin) {
            listaGrupos = consultarCalificacionesDELEGATE
                    .obtenerGruposPorCurso(this.curso, null);
        } else {
            listaGrupos = consultarCalificacionesDELEGATE
                    .obtenerGruposPorCurso(this.curso, usuarioActual);
        }
        return listaGrupos;
    }

    public List<UsuarioDTO> obtenerAlumnos(Integer indexGrupo) {
        this.grupo = this.listaGrupos.get(indexGrupo);
        this.listaAlumnos = consultarCalificacionesDELEGATE
                .obtenerAlumnosDeGrupo(grupo);
        return this.listaAlumnos;
    }

    public List<ExamenAsignadoDTO> obtenerExamenes(Integer indexAlumno) {
        this.alumno = this.listaAlumnos.get(indexAlumno);
        this.listaExamenes = consultarCalificacionesDELEGATE
                .obtenerExamenes(this.alumno, this.curso);
        return this.listaExamenes;
    }

    public ExamenAsignadoDTO obtenerExamen(Integer indexExamen) {
        this.examen = this.listaExamenes.get(indexExamen);
        this.examen = consultarCalificacionesDELEGATE
                .obtenerExamen(this.examen.getId());
        this.listaReactivos = examen.getReactivos();
        return examen;
    }

    public List<ReactivoAsignadoDTO> obtenerReactivos() {
        return this.listaReactivos;
    }

    public ReactivoAsignadoDTO obtenerReactivo(int indexReactivo) {
        return this.listaReactivos.get(indexReactivo);
    }

    public void liberarMemoria() {
        this.usuarioActual = null;
        this.listaCursos = null;
        this.curso = null;
        this.listaGrupos = null;
        this.grupo = null;
        this.alumno = null;
        this.listaExamenes = null;
        this.examen = null;
        this.listaReactivos = null;
    }
}
