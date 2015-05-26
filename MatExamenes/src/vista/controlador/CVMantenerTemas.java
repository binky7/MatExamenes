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

import control.delegate.MantenerTemasDELEGATE;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class CVMantenerTemas {

    /**
     * Objeto delegate del caso de uso Mantener Temas. Delega el trabajo a capas
     * inferiores para acceder a los datos de la aplicación
     */
    private final MantenerTemasDELEGATE mantenerTemasDELEGATE;

    /**
     * Lista de objetos de tipo CursoDTO para utilizar su información en las
     * vistas.
     */
    private List<CursoDTO> listaCursos;

    /**
     * Lista de objetos de tipo TemaDTO para utilizar su información en las
     * vistas.
     */
    private List<TemaDTO> listaTemas;

    /**
     * Objeto de tipo TemaDTO utilizado en modificar.
     */
    private TemaDTO temaSeleccionado;

    /**
     *
     */
    private boolean temaSinAsignar;

    /**
     * Objeto de tipo CursoDTO utilizado en modificar.
     */
    private CursoDTO cursoSeleccionado;

    /**
     * Crea un objeto CVMantenerTemas e inicializa sus atributos.
     */
    public CVMantenerTemas() {
        mantenerTemasDELEGATE = new MantenerTemasDELEGATE();
    }

    /**
     * Guarda el tema en la base de datos.
     *
     * @param tema El tema a guardar en la base de datos.
     * @param indexCurso Posición del curso al que pertenece el tema a guardar
     * en la lista de cursos.
     * @return Regresa el id del tema si se guardó exitósamente. Regresa null si
     * hubo problemas al guardar el tema.
     */
    public Integer guardarTema(TemaDTO tema, int indexCurso) {
        Integer id = mantenerTemasDELEGATE.guardarTema(tema);

        if (id != null) {
            CursoDTO objCurso = listaCursos.get(indexCurso);

            //Se busca la lista de temas pertenecientes al curso seleccionado
            //para agregar el tema a la lista.
            List<TemaDTO> temasDeCurso = obtenerTemasDeCurso(indexCurso);
            if (temasDeCurso == null || temasDeCurso.isEmpty()) {
                temasDeCurso = new ArrayList<TemaDTO>();
            }
            tema.setId(id);
            temasDeCurso.add(tema);
            objCurso.setTemas(temasDeCurso);
            mantenerTemasDELEGATE.actualizarCurso(objCurso);
        }
        return id;
    }

    /**
     * Obtener todos los cursos almacenados en la base de datos.
     *
     * @return Lista de todos los cursos almacenados en la base de datos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursosBusqueda;

        //Asigna los cursos obtenidos en la lista.
        listaCursosBusqueda = mantenerTemasDELEGATE.obtenerCursos();
        this.listaCursos = listaCursosBusqueda;

        return listaCursosBusqueda;
    }

    /**
     * Obtiene los temas del curso seleccionado.
     *
     * @param indexCurso Posición del curso en la lista de cursos del cual se
     * quiere obtener los temas.
     * @return Regresa lista de temas pertenecientes al curso seleccionado.
     * Regresa null si el curso seleccionado no tiene temas.
     */
    public List<TemaDTO> obtenerTemasDeCurso(int indexCurso) {
        List<TemaDTO> listaTemasBusqueda = null;

        if (listaCursos != null && !listaCursos.isEmpty()) {
            CursoDTO objCurso = listaCursos.get(indexCurso);
            listaTemasBusqueda = mantenerTemasDELEGATE
                    .obtenerTemasDeCurso(objCurso);
            this.listaTemas = listaTemasBusqueda;
            temaSinAsignar = false;
        }

        return listaTemasBusqueda;
    }

    /**
     * Obtiene un tema de la lista de temas.
     *
     * @param indexTema Posición del tema en la lista de temas.
     * @return El tema seleccionado.
     */
    public TemaDTO obtenerTema(int indexTema) {
        TemaDTO objTema = null;

        if (listaTemas != null && !listaTemas.isEmpty()) {
            objTema = listaTemas.get(indexTema);
        }

        temaSeleccionado = objTema;

        return temaSeleccionado;
    }

    /**
     * Modifica el tema en la base de datos.
     *
     * @param tema El tema a modificar en la base de datos.
     * @param indexCurso Posición del curso en la lista de cursos al cual
     * pertenece el tema a modificar.
     * @return Regresa verdadero si la modificación del tema se realizó
     * exitósamente. Regresa falso si hubo problemas al modificar el tema.
     */
    public boolean modificarTema(TemaDTO tema, int indexCurso) {
        boolean ok;

        if (this.temaSeleccionado != null) {
            tema.setId(temaSeleccionado.getId());

            ok = mantenerTemasDELEGATE.modificarTema(tema);
            if (ok) {
                CursoDTO objCurso = listaCursos.get(indexCurso);
                if (this.cursoSeleccionado != null) {
                    if (objCurso.getId() != this.cursoSeleccionado.getId()) {
                    //Cambió el curso.

                        //Eliminar la relación que existía entre el tema y el curso
                        //anterior.
                        List<TemaDTO> temasDeCurso
                                = obtenerTemasDeCurso(listaCursos.indexOf(this.cursoSeleccionado));
                        if (temasDeCurso != null && !temasDeCurso.isEmpty()) {
                            temasDeCurso.remove(tema);
                            this.cursoSeleccionado.setTemas(temasDeCurso);
                            mantenerTemasDELEGATE.actualizarCurso(this.cursoSeleccionado);
                        }

                        //Crear la relación entre el tema y el nuevo curso que
                        //se le asignó.
                        temasDeCurso = obtenerTemasDeCurso(indexCurso);
                        if (temasDeCurso == null || temasDeCurso.isEmpty()) {
                            temasDeCurso = new ArrayList<TemaDTO>();
                        }
                        temasDeCurso.add(tema);
                        objCurso.setTemas(temasDeCurso);
                        mantenerTemasDELEGATE.actualizarCurso(objCurso);
                    }
                } else {
                    //Crear la relación entre el tema y el nuevo curso que
                    //se le asignó.
                    List<TemaDTO> temasDeCurso = obtenerTemasDeCurso(indexCurso);
                    if (temasDeCurso == null || temasDeCurso.isEmpty()) {
                        temasDeCurso = new ArrayList<TemaDTO>();
                    }
                    temasDeCurso.add(tema);
                    objCurso.setTemas(temasDeCurso);
                    mantenerTemasDELEGATE.actualizarCurso(objCurso);
                }
            }
        } else {
            ok = false;
        }
        return ok;
    }

    /**
     * Elimina de la base de datos el tema seleccionado.
     *
     * @param indexTema Posición del tema a eliminar en la lista de temas.
     * @return Regresa verdadero si la eliminación se realizó exitósamente.
     * Regresa falso si hubo problemas con la eliminación.
     */
    public boolean eliminarTema(int indexTema) {

        boolean ok = false;

        if (listaTemas != null && !listaTemas.isEmpty()) {
            TemaDTO objTema = listaTemas.get(indexTema);
            ok = mantenerTemasDELEGATE.eliminarTema(objTema);
            if (ok) {
                listaTemas.remove(indexTema);
            }
        }

        return ok;
    }

    /**
     * Obtiene el curso al que pertenece el tema seleccionado.
     *
     * @param tema Tema del cual se quiere obtener el curso al que pertenece.
     * @return El curso al que pertenece el tema seleccionado.
     */
    public CursoDTO obtenerCursoPorTema(TemaDTO tema) {
        CursoDTO objCurso = mantenerTemasDELEGATE.obtenerCursoPorTema(tema);
        this.cursoSeleccionado = objCurso;
        return objCurso;
    }

    /**
     * Libera la memoria de los objetos utilizados en la vista de consultar.
     */
    public void liberarMemoriaConsultar() {
        listaCursos = null;
        listaTemas = null;
    }

    /**
     * Libera la memoria de los objetos utilizados en la vista de modificar.
     */
    public void liberarMemoriaModificar() {
        temaSeleccionado = null;
        temaSinAsignar = false;
    }

    /**
     * Verifica si el nombre del tema ingresado ya existe en la base de datos.
     *
     * @param nombreTema El nombre del tema ingresado.
     * @return Regresa verdadero si el nombre del tema ya existe en la base de
     * datos. Regresa falso si el nombre del tema no existe en la base de datos.
     */
    public boolean verificarExistencia(String nombreTema) {
        boolean existe;

        TemaDTO objTema = new TemaDTO();
        objTema.setNombre(nombreTema);
        existe = mantenerTemasDELEGATE.verificarExistencia(objTema);

        return existe;
    }

    /**
     *
     * Obtiene los temas que no pertenecen a ningún curso.
     *
     * @return Lista de temas que no pertenecen a ningún curso.
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemasBusqueda = null;

        //Se obtienen los temas sin asignar y se sustituye la lista
        listaTemasBusqueda = mantenerTemasDELEGATE.obtenerTemasSinAsignar();
        listaTemas = listaTemasBusqueda;

        if (listaTemasBusqueda != null && !listaTemasBusqueda.isEmpty()) {
            temaSinAsignar = true;
        }

        return listaTemasBusqueda;
    }

    /**
     * Regresa el atributo temaSeleccionado con los datos del tema actual en
     * modificación.
     *
     * @return El atributo temaSeleccionado.
     */
    public TemaDTO getTemaSeleccionado() {
        return this.temaSeleccionado;
    }

    /**
     *
     * @return Regresa verdadero si el tema seleccionado no tiene curso.
     */
    public boolean esTemaSinAsignar() {
        return this.temaSinAsignar;
    }
}
