/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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

import control.delegate.MantenerReactivosDELEGATE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;

/**
 * Esta clase se encarga de enviar las peticiones de las vistas del caso de uso
 * de Mantener Reactivos al delegate del mismo caso de uso, en el cuál se tiene
 * acceso a capas inferiores para acceder a la base de datos. También mantiene
 * en memoria los objetos dto que se utilizan para el caso de uso.
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class CVMantenerReactivos {
    /**
     * Objeto delegate del caso de uso Mantener Reactivos. Delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación
     */
    private final MantenerReactivosDELEGATE mantenerReactivosDELEGATE;
    /**
     * Lista de objetos ReactivoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<ReactivoDTO> listaReactivos;
    /**
     * Lista de objetos CursoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<CursoDTO> cursos;
    /**
     * Lista de objetos TemaDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<TemaDTO> temas;
    /**
     * Objeto TemaDTO que representa el tema del reactivo actual, tanto del
     * reactivo a guardar como el reactivo a modificar.
     */
    private TemaDTO temaReactivo;
    
    /**
     * Objeto ReactivoDTO que representa el reactivo actual, del que se obtienen
     * los datos para modificarlos.
     */
    private ReactivoDTO reactivo;
    
    /**
     * Crea un objeto CVMantenerReactivos e inicializa sus atributos
     */
    public CVMantenerReactivos() {
        mantenerReactivosDELEGATE = new MantenerReactivosDELEGATE();
    }
    
    /**
     * Obtiene los cursos existentes llamando al delegate del caso de uso.
     * 
     * @return una lista de CursoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null
     */
    public List<CursoDTO> obtenerCursos() {
        cursos = mantenerReactivosDELEGATE.obtenerCursos();
        return cursos;
    }
    
    /**
     * Este método sirve para almacenar el tema que será parte de los datos del
     * reactivo actual. El índexTema recibido sirve para buscar en la lista de
     * temas en este objeto el objeto tema correspondiente.
     * 
     * @param indexTema un entero que representa el índice en la vista que
     * está asociado con el objeto TemaDTO en la lista temas de este objeto
     */
    public void setTema(int indexTema) {
        if(temas != null && !temas.isEmpty()) {
            temaReactivo = temas.get(indexTema);
        }
    }
    
    /**
     * Obtiene los temas pertenecientes al curso seleccionado, en base al índice
     * ingresado
     * 
     * @param indexCurso el índice que representa al objeto CursoDTO en la lista
     * de cursos almacenada en este objeto.
     * 
     * @return una lista de TemaDTO con los temas del curso seleccionado, en caso
     * de que no exista ningún tema regresa null
     */
    public List<TemaDTO> obtenerTemasDeCurso(int indexCurso) {
        List<TemaDTO> listaTemas = null;
        
        if(cursos != null && !cursos.isEmpty()) {
            CursoDTO curso = cursos.get(indexCurso);
            listaTemas = mantenerReactivosDELEGATE.obtenerTemasDeCurso(curso);
            this.temas = listaTemas;
        }
        
        return listaTemas;
    }
    
    /**
     * Obtiene los reactivos pertenecientes al tema seleccionado, en base al
     * nombre del tema ingresado
     * @param nombreTema el nombre del tema del objeto TemaDTO en la lista
     * de temas almacenada en este objeto.
     * 
     * @return una lista de ReactivoDTO con los reactivos del tema seleccionado,
     * en caso de que no exista ningún reactivo regresa null
     */
    public List<ReactivoDTO> obtenerReactivosPorTema(String nombreTema) {
        TemaDTO tema = new TemaDTO();
        
        tema.setNombre(nombreTema);
        List<ReactivoDTO> reactivos = mantenerReactivosDELEGATE
                .obtenerReactivosPorTema(tema);
        this.listaReactivos = reactivos;
        
        return reactivos;
    }
 
    /**
     * Este método almacena el objeto ReactivoDTO, mediante la utilización del
     * delegate del caso de uso, para llamar a las capas inferiores.
     * 
     * @param reactivo el objeto ReactivoDTO que se desea almacenar
     * @return un objeto Integer que contiene el número de id generado en este
     * registro o null, en caso de que haya habido problemas al guardar
     */
    public Integer guardarReactivo(ReactivoDTO reactivo) {
        Integer id;
        
        //Antes de enviar la información se le agrega también el tema agregado
        //anteriormente
        reactivo.setTema(temaReactivo);
        id = mantenerReactivosDELEGATE.guardarReactivo(reactivo);
        
        return id;
    }
    
    /**
     * Este método es utilizado para obtener un objeto reactivo en base a su
     * índice, el cuál está asociado a alguna identificación en la vista, accede
     * a los datos de la aplicación por medio del delegate y regresa el objeto
     * ReactivoDTO
     * 
     * @param indexReactivo el índice que representa el objeto reactivo almacenado
     * en este objeto, el cuál no está completamente inicializado.
     * @return El objeto ReactivoDTO con sus relaciones completamente inicializadas
     */
    public ReactivoDTO obtenerReactivo(int indexReactivo) {
        ReactivoDTO objReactivo = null;
        
        if(listaReactivos != null && !listaReactivos.isEmpty()) {
            objReactivo = listaReactivos.get(indexReactivo);
            objReactivo = mantenerReactivosDELEGATE
                    .obtenerReactivo(objReactivo.getId());
            this.reactivo = objReactivo;
        }
        
        return objReactivo;
    }
 
    /**
     * Este método actualiza el objeto ReactivoDTO, mediante la utilización del
     * delegate del caso de uso, para llamar a las capas inferiores.
     * 
     * @param objReactivo el objeto ReactivoDTO que se desea actualizar
     * @return true si la actualización se realizó correctamente o false, en 
     * caso de que haya habido problemas al modificar
     */
    public boolean modificarReactivo(ReactivoDTO objReactivo) {
        
        //Pasa los datos del reactivo de entrada al reactivo de éste objeto y
        //después lo actualiza mediante el delegate
        this.reactivo.setNombre(objReactivo.getNombre());
        this.reactivo.setOpcionesIncorrectas(objReactivo.getOpcionesIncorrectas());
        this.reactivo.setRedaccion(objReactivo.getRedaccion());
        this.reactivo.setRespuesta(objReactivo.getRespuesta());
        this.reactivo.setFechaModificacion(objReactivo.getFechaModificacion());

        return mantenerReactivosDELEGATE.modificarReactivo(this.reactivo);
    }
    
    /**
     * Elimina todos los reactivos asociados a los índices contenidos en la lista
     * de entrada.
     * @param indexesReactivo la lista de índices de reactivos a eliminar
     * @return true si la operación se realizó correctamente o false si ocurrió
     * un error
     */
    public boolean eliminarReactivos(List<Integer> indexesReactivo) {
        boolean ok = false;
        
        if(listaReactivos != null && !listaReactivos.isEmpty()) {
            List<ReactivoDTO> objsReactivo = new ArrayList<>();
            
            //Agregar el reactivo de cada índice de la lista a la lista objReactivo
            for(int indexReactivo : indexesReactivo) {
                objsReactivo.add(listaReactivos.get(indexReactivo));
            }
            //Eliminar los reactivos
            ok = mantenerReactivosDELEGATE.eliminarReactivos(objsReactivo);
            //Si todo ocurrió correctamente
            if(ok) {
                //Ordenar los índices alrrevez para eliminarlos de la lista
                Collections.sort(indexesReactivo, Collections.reverseOrder());
                
                //Remover los reactivos eliminados de la listaReactivos
                for(int index : indexesReactivo) {
                    listaReactivos.remove(index);
                }
            }
        }
        
        return ok;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto relacionada a
     * la vista Consultar.
     */
    public void liberarMemoriaConsultar() {
        listaReactivos = null;
        cursos = null;
        temas = null;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto relacionada a
     * la vista Registrar.
     */
    public void liberarMemoriaRegistrar() {
        cursos = null;
        temas = null;
        temaReactivo = null;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto relacionada a
     * la vista Modificar.
     */
    public void liberarMemoriaModificar() {
        reactivo = null;
    }
    
}