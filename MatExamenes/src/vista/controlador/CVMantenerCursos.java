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

import control.delegate.MantenerCursosDELEGATE;
import java.util.List;
import modelo.dto.CursoDTO;

/**
 *
 * @author ivan
 */
public class CVMantenerCursos {

    /**
     * Objeto delegate del caso de uso Mantener Cursos. Delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación
     */
    private final MantenerCursosDELEGATE mantenerCursosDELEGATE;

    /**
     * Lista de objetos de tipo CursoDTO para utilizar su información en las
     * vistas.
     */
    private List<CursoDTO> listaCursos;

    /**
     * Objeto de tipo CursoDTO utilizado en modificar.
     */
    private CursoDTO cursoSeleccionado;

    /**
     * Crea un objeto CVMantenerCursos e inicializa sus atributos.
     */
    public CVMantenerCursos() {
        mantenerCursosDELEGATE = new MantenerCursosDELEGATE();
    }

    /**
     * Verifica si el nombre del curso ingresado ya existe en la base de datos.
     *
     * @param nombreCurso El nombre del curso ingresado.
     * @return Regresa verdadero si el nombre del curso ya existe en la base de
     * datos. Regresa falso si el nombre del curso no existe en la base de
     * datos.
     */
    public boolean verificarExistencia(String nombreCurso) {
        boolean existe;

        CursoDTO objCurso = new CursoDTO();
        objCurso.setNombre(nombreCurso);
        existe = mantenerCursosDELEGATE.verificarExistencia(objCurso);

        return existe;
    }

    /**
     * Guarda el curso en la base de datos.
     *
     * @param objCurso El curso a guardar en la base de datos.
     * @return Regresa el id del curso si se guardó exitósamente. Regresa null
     * si hubo problemas al guardar el curso.
     */
    public Integer guardarCurso(CursoDTO objCurso) {
        Integer id = null;
        cursoSeleccionado = new CursoDTO();
        cursoSeleccionado.setNombre(objCurso.getNombre());
        id = mantenerCursosDELEGATE.guardarCurso(cursoSeleccionado);

        return id;
    }

    /**
     * Libera la memoria de los objetos utilizados en las vistas de registrar y
     * modificar.
     */
    public void liberarMemoriaRegistrarModificar() {
        cursoSeleccionado = null;
    }

    /**
     * Libera la memoria de los objetos utilizados en la vista de consultar.
     */
    public void liberarMemoriaConsultar() {
        listaCursos = null;
    }

    /**
     * Obtiene todos los cursos almacenados en la base de datos.
     *
     * @return Regresa la lista de cursos si hay cursos almacenados en la base
     * de datos. Regresa null si no hay cursos almacenados en la base de datos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursosBusqueda = mantenerCursosDELEGATE.obtenerCursos();
        this.listaCursos = listaCursosBusqueda;

        return listaCursosBusqueda;
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
     * Modifica el curso en la base de datos.
     *
     * @param objCurso El curso a modificar en la base de datos.
     * @return Regresa verdadero si la modificación del curso se realizó
     * exitósamente. Regresa falso si hubo problemas al modificar el curso.
     */
    public boolean modificarCurso(CursoDTO objCurso) {
        objCurso.setId(cursoSeleccionado.getId());
        boolean ok = mantenerCursosDELEGATE.modificarCurso(objCurso);

        return ok;
    }

    /**
     * Elimina de la base de datos el curso seleccionado.
     *
     * @param indexCurso Posición del curso a eliminar en la lista de cursos.
     * @return Regresa verdadero si la eliminación se realizó exitósamente.
     * Regresa falso si hubo problemas con la eliminación.
     */
    public boolean eliminarCurso(int indexCurso) {
        CursoDTO objCurso = listaCursos.get(indexCurso);
        boolean ok = mantenerCursosDELEGATE.eliminarCurso(objCurso);
        if (ok) {
            listaCursos.remove(indexCurso);
        }

        return ok;
    }

    /**
     * Regresa el atributo cursoSeleccionado con los datos del curso actual en
     * modificación.
     *
     * @return El atributo cursoSeleccionado.
     */
    public CursoDTO getCursoSeleccionado() {
        return this.cursoSeleccionado;
    }
}
