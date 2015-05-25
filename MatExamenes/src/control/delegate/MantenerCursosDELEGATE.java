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
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import remoteAccess.Enlace;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class MantenerCursosDELEGATE {

    /**
     * Verifica si el nombre del curso ingresado ya existe en la base de datos.
     *
     * @param curso El nombre del curso ingresado.
     * @return Regresa verdadero si el nombre del curso ya existe en la base de
     * datos. Regresa falso si el nombre del curso no existe en la base de
     * datos.
     */
    public boolean verificarExistencia(CursoDTO curso) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().verificarExistencia(curso);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }

    /**
     * Guarda el curso en la base de datos.
     *
     * @param curso El curso a guardar en la base de datos.
     * @return Regresa el id del curso si se guardó exitósamente. Regresa null
     * si hubo problemas al guardar el curso.
     */
    public Integer guardarCurso(CursoDTO curso) {
        Integer id = null;
        try {
            id = Enlace.getPersistencia().guardarEntidad(curso);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return id;
    }

    /**
     * Obtiene todos los cursos almacenados en la base de datos.
     *
     * @return Regresa la lista de cursos si hay cursos almacenados en la base
     * de datos. Regresa null si no hay cursos almacenados en la base de datos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> cursos = null;

        try {
            cursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return cursos;
    }

    /**
     * Modifica el curso en la base de datos.
     *
     * @param curso El curso a modificar en la base de datos.
     * @return Regresa verdadero si la modificación del curso se realizó
     * exitósamente. Regresa falso si hubo problemas al modificar el curso.
     */
    public boolean modificarCurso(CursoDTO curso) {
        boolean ok = false;

        try {
            ok = Enlace.getPersistencia().modificarEntidad(curso);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }

    /**
     * Elimina de la base de datos el curso seleccionado.
     *
     * @param curso El curso a eliminar.
     * @return Regresa verdadero si la eliminación se realizó exitósamente.
     * Regresa falso si hubo problemas con la eliminación.
     */
    public boolean eliminarCurso(CursoDTO curso) {
        boolean ok = false;

        try {
            ok = Enlace.getPersistencia().eliminarEntidad(curso);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }
}
