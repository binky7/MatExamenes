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
public class MantenerTemasDELEGATE {

    /**
     * Guarda el tema en la base de datos.
     *
     * @param tema El tema a guardar en la base de datos.
     * @return Regresa el id del tema si se guardó exitósamente. Regresa null si
     * hubo problemas al guardar el tema.
     */
    public Integer guardarTema(TemaDTO tema) {
        Integer id = null;
        try {
            id = Enlace.getPersistencia().guardarEntidad(tema);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return id;
    }
    
    public CursoDTO obtenerCurso(int idCurso) {
        CursoDTO curso = null;
        try {
            curso = Enlace.getPersistencia().obtenerCurso(idCurso);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return curso;
    }

    /**
     * Obtener todos los cursos almacenados en la base de datos.
     *
     * @return Lista de todos los cursos almacenados en la base de datos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = null;

        try {
            listaCursos = Enlace.getPersistencia()
                    .obtenerEntidades(CursoDTO.class);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }

        return listaCursos;
    }

    /**
     * Obtiene los temas del curso seleccionado.
     *
     * @param curso El curso seleccionado.
     * @return Regresa lista de temas pertenecientes al curso seleccionado.
     * Regresa null si el curso seleccionado no tiene temas.
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso, int bloque) {
        List<TemaDTO> listaTemas = null;

        try {
            listaTemas = Enlace.getPersistencia().obtenerTemasDeCurso(curso, bloque);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }

        return listaTemas;
    }

    /**
     * Modifica el tema en la base de datos.
     *
     * @param tema El tema a modificar en la base de datos.
     * @return Regresa verdadero si la modificación del tema se realizó
     * exitósamente. Regresa falso si hubo problemas al modificar el tema.
     */
    public boolean modificarTema(TemaDTO tema) {
        boolean ok = false;

        try {
            ok = Enlace.getPersistencia().modificarEntidad(tema);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }

    /**
     * Elimina de la base de datos el tema seleccionado.
     *
     * @param tema El tema a eliminar.
     * @return Regresa verdadero si la eliminación se realizó exitósamente.
     * Regresa falso si hubo problemas con la eliminación.
     */
    public boolean eliminarTema(TemaDTO tema) {
        boolean ok = false;

        try {
            ok = Enlace.getPersistencia().eliminarEntidad(tema);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }

    /**
     * Actualiza la información del curso seleccionado.
     *
     * @param curso El curso seleccionado.
     * @return Regresa verdadero si la actualización de los datos se realizó
     * exitósamente. Regresa falso si hubo problemas con la actualización de los
     * datos.
     */
    public boolean actualizarCurso(CursoDTO curso) {
        boolean ok = false;

        try {
            ok = Enlace.getPersistencia().modificarEntidad(curso);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }

    /**
     * Obtiene el curso al que pertenece el tema seleccionado.
     *
     * @param tema El tema seleccionado.
     * @return El curso al que pertenece el tema seleccionado.
     */
    public CursoDTO obtenerCursoPorTema(TemaDTO tema) {
        CursoDTO objCurso = null;

        try {
            objCurso = Enlace.getPersistencia().obtenerCursoPorTema(tema);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return objCurso;
    }

    /**
     * Verifica si el nombre del tema ingresado ya existe en la base de datos.
     *
     * @param tema El curso del cual se quiere verificar su existencia.
     * @return Regresa verdadero si el nombre del tema ya existe en la base de
     * datos. Regresa falso si el nombre del tema no existe en la base de datos.
     */
    public boolean verificarExistencia(TemaDTO tema) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().verificarExistencia(tema);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }

        return ok;
    }

    /**
     *
     * Obtiene los temas que no pertenecen a ningún curso.
     *
     * @return Lista de temas que no pertenecen a ningún curso.
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemas = null;

        try {
            listaTemas = Enlace.getPersistencia().obtenerTemasSinAsignar();
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }

        return listaTemas;
    }
}
