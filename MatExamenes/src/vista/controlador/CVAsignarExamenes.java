/*
 * Copyright (C) 2015 E. Iván Mariscal Martínez
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class CVAsignarExamenes {

    /**
     * Objeto delegate del caso de uso Asignar Exámenes. Delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación
     */
    private final AsignarExamenesDELEGATE asignarExamenesDELEGATE;

    /**
     * Lista de objetos de tipo CursoDTO para utilizar su información en la
     * vista.
     */
    private List<CursoDTO> listaCursos;

    /**
     * Curso seleccionado para la asignación del examen.
     */
    private CursoDTO cursoSeleccionado;

    /**
     * Lista de objetos de tipo ExamenDTO para utilizar su información en la
     * vista.
     */
    private List<ExamenDTO> listaExamenes;

    /**
     * Examen seleccionado para la asignación del examen.
     */
    private ExamenDTO examenSeleccionado;

    /**
     * Lista de objetos de tipo GrupoDTO para utilizar su información en la
     * vista.
     */
    private List<GrupoDTO> listaGrupos;

    /**
     * Lista de objetos de tipo UsuarioDTO de los alumnos pertenecientes al
     * grupo seleccionado.
     */
    private List<UsuarioDTO> listaAlumnos;

    /**
     * Crea un objeto CVAsignarExamenes e inicializa sus atributos.
     */
    public CVAsignarExamenes() {
        asignarExamenesDELEGATE = new AsignarExamenesDELEGATE();
    }

    /**
     * Obtiene los cursos almacenados en la base de datos dependiendo del tipo
     * de usuario que sea el usuario actual.
     *
     * @param usuarioActual El usuario actual en el sistema.
     * @return Regresa la lista de cursos si hay cursos disponibles dependiendo
     * del tipo de usuario del usuario actual. Regresa null si no hay cursos
     * disponibles.
     */
    public List<CursoDTO> obtenerCursos(UsuarioDTO usuarioActual) {
        List<CursoDTO> listaCursosBusqueda = null;

        if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin) {
            listaCursosBusqueda = asignarExamenesDELEGATE.obtenerCursos();
        } else if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro) {
            listaCursosBusqueda
                    = asignarExamenesDELEGATE.obtenerCursosDeGrupo(usuarioActual);
        }

        listaCursos = listaCursosBusqueda;
        return listaCursosBusqueda;
    }

    /**
     * Obtiene los exámenes del curso seleccionado y dependiendo del tipo de
     * usuario que sea el usuario actual.
     *
     * @param curso El curso seleccionado.
     * @param usuarioActual El usuario actual en el sistema.
     * @return Regresa la lista de exámenes si hay exámenes disponibles
     * dependiendo del tipo de usuario del usuario actual. Regresa null si no
     * hay exámenes disponibles.
     */
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

    /**
     * Obtiene un curso de la lista de cursos.
     *
     * @param indexCurso Posición del curso en la lista de cursos.
     * @return El curso seleccionado.
     */
    public CursoDTO obtenerCurso(int indexCurso) {
        CursoDTO objCurso = null;

        if (listaCursos != null || !listaCursos.isEmpty()) {
            objCurso = listaCursos.get(indexCurso);
            cursoSeleccionado = objCurso;
        }

        return objCurso;
    }

    /**
     * Obtiene un examen de la lista de exámenes.
     *
     * @param indexExamen Posición del examen en la lista de exámenes.
     * @return El examen seleccionado.
     */
    public ExamenDTO obtenerExamen(int indexExamen) {
        ExamenDTO objExamen = null;

        if (listaExamenes != null || !listaExamenes.isEmpty()) {
            objExamen = asignarExamenesDELEGATE
                    .obtenerExamen(listaExamenes.get(indexExamen).getId());

            examenSeleccionado = objExamen;
        }

        return objExamen;
    }

    /**
     * Obtiene los grupos asociados al curso seleccionado y dependiendo del tipo
     * de usuario del usuario actual.
     *
     * @param usuarioActual El usuario actual en el sistema.
     * @return Regresa la lista de grupos si hay grupos disponibles dependiendo
     * del tipo de usuario del usuario actual. Regresa null si no hay gupos
     * disponibles.
     */
    public List<GrupoDTO> obtenerGruposPorCurso(UsuarioDTO usuarioActual) {
        if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin) {
            usuarioActual = null;
        }

        List<GrupoDTO> listaGruposBusqueda = asignarExamenesDELEGATE
                .obtenerGruposPorCurso(cursoSeleccionado, usuarioActual);
        listaGrupos = listaGruposBusqueda;

        return listaGruposBusqueda;
    }

    /**
     * Obtiene la lista de alumnos pertenecientes al grupo seleccionado.
     *
     * @param indexGrupo Posición del grupo seleccionado en la lista de grupos.
     * @return Regresa la lista de alumnos si hay alumnos en el grupo
     * seleccionado. Regresa null si no hay alumnos en el grupo seleccionado.
     */
    public List<UsuarioDTO> obtenerAlumnosDeGrupo(int indexGrupo) {
        List<UsuarioDTO> listaAlumnosBusqueda = null;

        listaAlumnosBusqueda = asignarExamenesDELEGATE
                .obtenerAlumnosDeGrupo(listaGrupos.get(indexGrupo));

        if (listaAlumnosBusqueda.isEmpty()) {
            listaAlumnosBusqueda = null;
        }

        listaAlumnos = listaAlumnosBusqueda;

        return listaAlumnosBusqueda;
    }

    /**
     * Obtiene el alumno seleccionado de la lista de alumnos.
     *
     * @param usuario El alumno seleccionado.
     * @return El alumno seleccionado de la lista de alumnos.
     */
    public UsuarioDTO obtenerAlumno(String usuario) {
        UsuarioDTO alumno = null;

        for (UsuarioDTO alumnoBusqueda : listaAlumnos) {
            if (alumnoBusqueda.getUsuario().compareToIgnoreCase(usuario) == 0) {
                alumno = alumnoBusqueda;
            }
        }

        return alumno;
    }

    /**
     * Obtiene el examen seleccionado.
     *
     * @return El examen seleccionado.
     */
    public ExamenDTO getExamenSeleccionado() {
        return this.examenSeleccionado;
    }

    /**
     * Almacena la lista de exámenes asignados en la base de datos.
     *
     * @param listaExamenesAsignados La lista de exámenes asignados.
     * @return Regresa verdadero si los exámenes asignados fueron guardados
     * exitósamente. Regresa null si hubo errores al guardar los exámenes
     * asignados.
     */
    public boolean asignarExamenes(List<ExamenAsignadoDTO> listaExamenesAsignados) {
        boolean ok = false;
        ok = asignarExamenesDELEGATE.asignarExamenes(listaExamenesAsignados);

        return ok;
    }

    /**
     * Obtiene la clave seleccionada del examen.
     *
     * @param claveExamenPK Id de la clave seleccionada.
     * @return La clave seleccionada del examen.
     */
    public ClaveExamenDTO obtenerClaveExamen(ClaveExamenPK claveExamenPK) {
        ClaveExamenDTO claveExamen = null;

        claveExamen = asignarExamenesDELEGATE.obtenerClaveExamen(claveExamenPK);

        return claveExamen;
    }
}
