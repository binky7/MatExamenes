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
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class AsignarExamenesDELEGATE {

    /**
     * Obtiene todos los cursos almacenados en la base de datos.
     *
     * @return Regresa la lista de cursos si hay cursos almacenados en la base
     * de datos. Regresa null si no hay cursos almacenados en la base de datos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = null;

        try {
            listaCursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return listaCursos;
    }

    /**
     * Obtiene los cursos asociados con el maestro actual.
     *
     * @param maestro El maestro actual en el sistema.
     * @return Regresa lista de cursos asociados con el maestro actual. Regresa
     * null si no hay cursos asociados con el maestro actual.
     */
    public List<CursoDTO> obtenerCursosDeGrupo(UsuarioDTO maestro) {
        List<CursoDTO> listaCursos = null;

        try {
            listaCursos = Enlace.getPersistencia().obtenerCursosDeGrupo(maestro);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return listaCursos;
    }

    /**
     * Obtiene los exámenes pertenecientes al curso seleccionado y dependiendo
     * del tipo de usuario del usuario actual.
     *
     * @param curso El curso seleccionado.
     * @param todos Es verdadero si el usuario actual es de tipo Admin. Es falso
     * si el usuario actual es de tipo Maestro.
     * @param usuarioActual El usuario actual en el sistema.
     * @return Regresa la lista de exámenes si hay exámenes disponibles
     * dependiendo del tipo de usuario del usuario actual. Regresa null si no
     * hay exámenes disponibles.
     */
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

    /**
     * Obtiene un examen en específico de los exámenes almacenados en la base de
     * datos.
     *
     * @param idExamen Id del examen seleccionado.
     * @return El examen seleccionado.
     */
    public ExamenDTO obtenerExamen(int idExamen) {
        ExamenDTO examen = null;
        try {
            examen = Enlace.getPersistencia()
                    .obtenerExamenIncompleto(idExamen);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return examen;
    }

    /**
     * Obtiene los grupos asociados al curso seleccionado y dependiendo del tipo
     * de usuario del usuario actual.
     *
     * @param curso El curso seleccionado.
     * @param usuarioActual El usuario actual en el sistema.
     * @return Regresa la lista de grupos si hay grupos disponibles dependiendo
     * del tipo de usuario del usuario actual. Regresa null si no hay gupos
     * disponibles.
     */
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

    /**
     * Obtiene la lista de alumnos pertenecientes al grupo seleccionado.
     *
     * @param grupo El grupo seleccionado.
     * @return Regresa la lista de alumnos si hay alumnos en el grupo
     * seleccionado. Regresa null si no hay alumnos en el grupo seleccionado.
     */
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

        try {
            ok = Enlace.getPersistencia()
                    .asignarExamenes(listaExamenesAsignados);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

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

        try {
            claveExamen = Enlace.getPersistencia()
                    .obtenerClaveExamen(claveExamenPK);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return claveExamen;
    }
}
