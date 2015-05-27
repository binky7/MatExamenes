/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 * Esta clase se encarga de enviar las peticiones del control vista del caso de
 * uso de Consultar Calificaciones a la interface Persistencia, la cuál oculta
 * la forma en la cuál se tiene acceso a capas inferiores, el Delegate se
 * encarga de delegar el trabajo a esta interface y obtener los datos que se le
 * pidan.
 *
 * @author Fernando Enrique Avendaño Hernández.
 * @version 1 18 Mayo 2015.
 */
public class ConsultarCalificacionesDELEGATE {

    /**
     * Obtiene todos los cursos disponibles.
     *
     * @return lista de cursos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> cursos = null;
        try {
            cursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    /**
     * Obtiene los cursos que imparte un maestro en especifico.
     *
     * @param maestro el maestro del cual se obtendran los cursos.
     * @return lista de cursos.
     */
    public List<CursoDTO> obtenerCursosPorMaestro(UsuarioDTO maestro) {
        List<CursoDTO> cursos = null;
        try {
            cursos = Enlace.getPersistencia().obtenerCursosDeGrupo(maestro);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return cursos;
    }

    /**
     * Obtiene los grupos del curso seleccionado dependiendo del usuario actual.
     *
     * @param curso el curso.
     * @param usuarioActual el usuario actual.
     * @return lista de grupos.
     */
    public List<GrupoDTO> obtenerGruposPorCurso(CursoDTO curso, UsuarioDTO usuarioActual) {
        List<GrupoDTO> grupos = null;
        try {
            grupos = Enlace.getPersistencia().obtenerGruposPorCurso(curso, usuarioActual);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return grupos;
    }

    /**
     * Obtiene los alumnos del grupo que recibe.
     *
     * @param grupo el grupo.
     * @return lista de alumnos.
     */
    public List<UsuarioDTO> obtenerAlumnosDeGrupo(GrupoDTO grupo) {
        List<UsuarioDTO> alumnos = null;
        try {
            alumnos = Enlace.getPersistencia().obtenerAlumnosDeGrupo(grupo);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName()
            ).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    /**
     * Obtiene los examenes contestados del alumno y el curso seleccionados.
     *
     * @param alumno el alumno.
     * @param curso el curso.
     * @return lista de examenes.
     */
    public List<ExamenAsignadoDTO> obtenerExamenes(UsuarioDTO alumno, CursoDTO curso) {
        List<ExamenAsignadoDTO> examenes = null;
        try {
            examenes = Enlace.getPersistencia().obtenerExamenesContestados(alumno, curso);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return examenes;
    }

    /**
     * Recibe el id del examen y obtiene el examen al que pertenece el id.
     *
     * @param id el id del examen.
     * @return el examen.
     */
    public ExamenAsignadoDTO obtenerExamen(ExamenAsignadoPK id) {
        ExamenAsignadoDTO examen = null;
        try {
            examen = Enlace.getPersistencia().obtenerExamenContestado(id);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ConsultarCalificacionesDELEGATE.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return examen;
    }
}
