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
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.CursoTemaDTO;
import modelo.dto.ReactivoDTO;
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
     * Lista de objetos de tipo TemaDTO que representan los temas que no están
     * asociados s ningún curso.
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
     * @param bloque El bloque al que pertenece el tema en el curso.
     * @return Regresa el id del tema si se guardó exitósamente. Regresa null si
     * hubo problemas al guardar el tema.
     */
    public Integer guardarTema(TemaDTO tema, int indexCurso, int bloque) {
        Integer id = mantenerTemasDELEGATE.guardarTema(tema);

        if (id != null) {
            tema.setId(id);
            CursoDTO objCurso = listaCursos.get(indexCurso);

            //Obtiene el curso completo con todas sus relaciones para poder
            //asociar el nuevo tema creado.
            objCurso = mantenerTemasDELEGATE.obtenerCurso(objCurso.getId());

            CursoTemaDTO nuevoTema = new CursoTemaDTO();
            nuevoTema.setCurso(objCurso);
            nuevoTema.setTema(tema);
            nuevoTema.setBloque(bloque);
            objCurso.addTema(nuevoTema);

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
     * Obtiene los temas del curso seleccionado dependiendo del bloque
     * seleccionado.
     *
     * @param indexCurso Posición del curso en la lista de cursos del cual se
     * quiere obtener los temas.
     * @param bloque El bloque seleccionado.
     * @return Regresa lista de temas pertenecientes al curso seleccionado
     * dependiendo del bloque seleccionado.
     * Regresa null si el curso seleccionado no tiene temas.
     */
    public List<TemaDTO> obtenerTemasDeCurso(int indexCurso, int bloque) {
        List<TemaDTO> listaTemasBusqueda = null;

        if (listaCursos != null && !listaCursos.isEmpty()) {
            CursoDTO objCurso = listaCursos.get(indexCurso);
            listaTemasBusqueda = mantenerTemasDELEGATE
                    .obtenerTemasDeCurso(objCurso, bloque);
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
    public boolean modificarTema(TemaDTO tema, int indexCurso, int bloque) {
        boolean ok;

        if (this.temaSeleccionado != null) {
            tema.setId(temaSeleccionado.getId());

            ok = mantenerTemasDELEGATE.modificarTema(tema);
            if (ok) {
                //Se obtiene el curso que está seleccionado en la vista de moficar.
                CursoDTO objCurso = listaCursos.get(indexCurso);
                
                //Si cursoSeleccionado no es null, significa que el tema sí está
                //asociado a un curso.
                if (this.cursoSeleccionado != null) {
                    //Verificar si el curso seleccionado en la vista es el mismo
                    //curso al que originalmente estaba asociado el tema a modificar.
                    if (objCurso.getId() != this.cursoSeleccionado.getId()) {
                        //El curso seleccionado cambió.

                        //Eliminar la relación que existía entre el tema y el curso
                        //anterior.
                        List<CursoTemaDTO> temasDeCurso
                                = this.cursoSeleccionado.getTemas();
                        CursoTemaDTO temaEliminado = null;
                        if (temasDeCurso != null && !temasDeCurso.isEmpty()) {
                            //Si la lista de temas no está vacía se hace un
                            //recorrido por toda la lista de temas para encontrar
                            //el tema que se eliminará de la relación.
                            for (CursoTemaDTO j : temasDeCurso) {
                                //Durante el ciclo se pregunta si el id del tema
                                //actual en el ciclo es igual al id del tema
                                //del cual se quiere eliminar la relación.
                                if (j.getTema().getId() == tema.getId()) {
                                    //Si se encuentra el tema con el id buscado
                                    //se guarda para ser posteriormente eliminado.
                                    temaEliminado = j;
                                }
                            }
                            temasDeCurso.remove(temaEliminado);
                            this.cursoSeleccionado.setTemas(temasDeCurso);
                            mantenerTemasDELEGATE.actualizarCurso(this.cursoSeleccionado);
                        }

                        //Crear la relación entre el tema y el nuevo curso que
                        //se le asignó.
                        objCurso = mantenerTemasDELEGATE.obtenerCurso(objCurso.getId());
                        CursoTemaDTO nuevoTema = new CursoTemaDTO();
                        nuevoTema.setCurso(objCurso);
                        nuevoTema.setTema(tema);
                        nuevoTema.setBloque(bloque);
                        objCurso.addTema(nuevoTema);
                        mantenerTemasDELEGATE.actualizarCurso(objCurso);
                    } else {
                        //El curso no cambió, por lo que se verifica si el
                        //bloque cambió o no.
                        int bloqueTemaSeleccionado = 0;
                        List<CursoTemaDTO> temasDeCurso
                                = this.cursoSeleccionado.getTemas();
                        for (CursoTemaDTO j : temasDeCurso) {
                            if (j.getTema().getId() == tema.getId()) {
                                //Si se encuentra el tema con el id buscado
                                //se guarda su bloque para ser posteriormente
                                //verificar si cambió o no.
                                bloqueTemaSeleccionado = j.getBloque();
                            }
                        }

                        //Si el bloque del tema a modificar es diferente del
                        //seleccionado en la vista, significa que el bloque
                        //cambió y se debe actualizar.
                        if (bloqueTemaSeleccionado != bloque) {
                            for (CursoTemaDTO j : temasDeCurso) {
                                if (j.getTema().getId() == tema.getId()) {
                                    j.setBloque(bloque);
                                }
                            }
                            this.cursoSeleccionado.setTemas(temasDeCurso);
                            mantenerTemasDELEGATE.actualizarCurso(this.cursoSeleccionado);
                        }
                    }
                } else {
                    //Crear la relación entre el tema y el nuevo curso que
                    //se le asignó.
                    objCurso = listaCursos.get(indexCurso);
                    objCurso = mantenerTemasDELEGATE.obtenerCurso(objCurso.getId());
                    CursoTemaDTO nuevoTema = new CursoTemaDTO();
                    nuevoTema.setCurso(objCurso);
                    nuevoTema.setTema(tema);
                    nuevoTema.setBloque(bloque);
                    objCurso.addTema(nuevoTema);
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
        cursoSeleccionado = null;
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
    
    /**
     * Verifica si el tema tiene reactivos asociados.
     * 
     * @param indexTema el tema del cuál se quiere saber si tiene reactivos asociados.
     * @return true si el tema tiene reactivos asociados. Regresa false si el
     * tema no tiene reactivos asociados.
     */
    public boolean tieneRactivosAsociados(int indexTema) {
        boolean tieneReactivos = false;
        TemaDTO objTema = listaTemas.get(indexTema);
        
        List<ReactivoDTO> listaReactivos = 
                mantenerTemasDELEGATE.obtenerReactivosPorTema(objTema);
        if(listaReactivos != null && !listaReactivos.isEmpty()) {
            tieneReactivos = true;
        }
        
        return tieneReactivos;
    }
}
