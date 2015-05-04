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
    private List<GrupoDTO> listaGrupos;
    private GrupoDTO grupo;
    private List<UsuarioDTO> listaAlumnos;

    public CVConsultarCalificaciones() {
        consultarCalificacionesDELEGATE = new ConsultarCalificacionesDELEGATE();
    }

    public List<CursoDTO> obtenerCursos(UsuarioDTO actual) {
        listaCursos = null;
        usuarioActual = actual;
        if (actual.getTipo() == Tipo.Admin) {
            listaCursos = consultarCalificacionesDELEGATE.obtenerCursos();
        } else {
            listaCursos = consultarCalificacionesDELEGATE.obtenerCursosPorMaestro(actual);
        }
        return listaCursos;
    }

    public List<GrupoDTO> obtenerGrupos(String nombreCurso) {
        listaGrupos = null;
        CursoDTO curso = null;
        for (CursoDTO c : listaCursos) {
            if (c.getNombre().equalsIgnoreCase(nombreCurso)) {
                curso = c;
            }
        }
        if (usuarioActual.getTipo() == Tipo.Admin) {
            listaGrupos = consultarCalificacionesDELEGATE
                    .obtenerGruposPorCurso(curso);
        } else {
            listaGrupos = consultarCalificacionesDELEGATE
                    .obtenerGruposPorCurso(curso, usuarioActual);
        }
        return listaGrupos;
    }

    public List<UsuarioDTO> obtenerAlumnos(Integer indexGrupo) {
        this.grupo = this.listaGrupos.get(indexGrupo);
        this.listaAlumnos = this.grupo.getAlumnos();
        return this.listaAlumnos;
    }

    public List<ExamenAsignadoDTO> obtenerExamenes(Integer indexAlumno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
