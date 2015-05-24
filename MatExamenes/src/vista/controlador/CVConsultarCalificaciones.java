/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
 * Esta clase se encarga de enviar las peticiones de las vistas del caso de uso
 * de Consultar Calificaciones al delegate del mismo caso de uso, en el cuál se
 * tiene acceso a capas inferiores para acceder a la base de datos. También
 * mantiene en memoria los objetos dto que se utilizan para el caso de uso.
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class CVConsultarCalificaciones {

    /**
     * Objeto delegate del caso de uso Mantener Grupos, delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación.
     */
    private ConsultarCalificacionesDELEGATE consultarCalificacionesDELEGATE;

    /**
     * Objeto UsuarioDTO que se utilizará como almacén temporal para utilizar su
     * información en las vistas.
     */
    private UsuarioDTO usuarioActual;

    /**
     * Lista de objetos CursoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<CursoDTO> listaCursos;

    /**
     * Objeto CursoDTO que se utilizará como almacén temporal para utilizar su
     * información en las vistas.
     */
    private CursoDTO curso;

    /**
     * Lista de objetos GrupoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<GrupoDTO> listaGrupos;

    /**
     * Objeto GrupoDTO que se utilizará como almacén temporal para utilizar su
     * información en las vistas.
     */
    private GrupoDTO grupo;

    /**
     * Lista de objetos UsuarioDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<UsuarioDTO> listaAlumnos;

    /**
     * Objeto UsuarioDTO que se utilizará como almacén temporal para utilizar su
     * información en las vistas.
     */
    private UsuarioDTO alumno;

    /**
     * Lista de objetos ExamenAsignadoDTO que se utilizará como almacén temporal
     * para utilizar su información en las vistas.
     */
    private List<ExamenAsignadoDTO> listaExamenes;

    /**
     * Objeto ExamenAsignadoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private ExamenAsignadoDTO examen;

    /**
     * Lista de objetos ReactivoAsignadoDTO que se utilizará como almacén
     * temporal para utilizar su información en las vistas.
     */
    private List<ReactivoAsignadoDTO> listaReactivos;

    /**
     * Crea un objeto CVMantenerReactivos e inicializa sus atributos.
     */
    public CVConsultarCalificaciones() {
        consultarCalificacionesDELEGATE = new ConsultarCalificacionesDELEGATE();
    }

    /**
     * Recibe el usuario actual del sistema y obtiene todos los cursos si se
     * trata de un administrador o los cursos del maestro en su caso.
     *
     * @param actual recibe el usuario actual del sistema.
     * @return regresa la lista de cursos.
     */
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

    /**
     * Recibe el nombre del curso seleccionado y obtiene dependiendo del usuario
     * actual los grupos en los que se imparten ese curso.
     *
     * @param nombreCurso recibe el nombre del curso.
     * @return regresa la lista de grupos.
     */
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

    /**
     * Recibe el index del grupo seleccionado y obtiene la lista de alumnos de
     * ese grupo.
     *
     * @param indexGrupo recibe el indice del grupo.
     * @return regresa la lista de alumnos.
     */
    public List<UsuarioDTO> obtenerAlumnos(Integer indexGrupo) {
        this.grupo = this.listaGrupos.get(indexGrupo);
        this.listaAlumnos = consultarCalificacionesDELEGATE
                .obtenerAlumnosDeGrupo(grupo);
        return this.listaAlumnos;
    }

    /**
     * Recibe el indice del alumno seleccionado y obtiene la lista de examenes
     * contestados por el alumno.
     *
     * @param indexAlumno recibe el indice del alumno.
     * @return regresa la lista de examenes contestados.
     */
    public List<ExamenAsignadoDTO> obtenerExamenes(Integer indexAlumno) {
        this.alumno = this.listaAlumnos.get(indexAlumno);
        this.listaExamenes = consultarCalificacionesDELEGATE
                .obtenerExamenes(this.alumno, this.curso);
        return this.listaExamenes;
    }

    /**
     * Recibe el indice del examen seleccionado y obtiene el examen con todos
     * sus atributos.
     *
     * @param indexExamen recibe el indice del examen.
     * @return regresa el examen.
     */
    public ExamenAsignadoDTO obtenerExamen(Integer indexExamen) {
        this.examen = this.listaExamenes.get(indexExamen);
        this.examen = consultarCalificacionesDELEGATE
                .obtenerExamen(this.examen.getId());
        this.listaReactivos = examen.getReactivos();
        return examen;
    }

    /**
     * Regresa la lista de reactivos del examen actual.
     *
     * @return regresa la lista de reactivos.
     */
    public List<ReactivoAsignadoDTO> obtenerReactivos() {
        return this.listaReactivos;
    }

    /**
     * Recibe el indice del reactivo seleccionado y regresa el reactivo con
     * todos sus atributos.
     *
     * @param indexReactivo recibe el indice del reactivo.
     * @return regresa el reactivo.
     */
    public ReactivoAsignadoDTO obtenerReactivo(int indexReactivo) {
        return this.listaReactivos.get(indexReactivo);
    }

    /**
     * Libera la memoria de las variables.
     */
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
