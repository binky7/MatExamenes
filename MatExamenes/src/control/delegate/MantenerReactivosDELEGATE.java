/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import remoteAccess.Enlace;

/**
 * Esta clase se encarga de enviar las peticiones del control vista del caso de
 * uso de Mantener Reactivos a la interface Persistencia, la cuál oculta la forma
 * en la cuál se tiene acceso a capas inferiores. El Delegate se encarga de
 * delegar el trabajo a esta interface y obtener los datos que se le pidan.
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class MantenerReactivosDELEGATE {
    
    /**
     * Obtiene los cursos existentes llamando a la interface Persistencia.
     * 
     * @return una lista de CursoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = null;
        
        try {
            //Llama a la Persistencia y requiere objetos de clase CursoDTO
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
     * Obtiene los temas pertenecientes al curso seleccionado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener sus temas
     * 
     * @return una lista de TemaDTO con los temas del curso seleccionado, en caso
     * de que no exista ningún tema regresa null
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) {
        List<TemaDTO> temas = null;
        try{
            //Por medio de la interface Persistencia se obtiene los temas del curso
            temas = Enlace.getPersistencia().obtenerTemasDeCurso(curso);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return temas;
    }
    
    /**
     * Obtiene los temas pertenecientes al curso y al bloque seleccionado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los temas
     * @param bloque un entero que representa el bloque del que se quieren obtener
     * los temas
     * 
     * @return una lista de TemaDTO con los temas del curso y bloque seleccionados,
     * en caso de que no exista ningún tema regresa null
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso, int bloque) {
        List<TemaDTO> temas = null;
        try{
            //Por medio de la interface Persistencia se obtiene los temas del curso
            temas = Enlace.getPersistencia().obtenerTemasDeCurso(curso, bloque);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return temas;
    }
    
    /**
     * Este método es utilizado para obtener el objeto reactivo perteneciente
     * al id ingresado
     * 
     * @param idReactivo el id del reactivo que se quiere obtener
     * @return El objeto ReactivoDTO con sus relaciones completamente inicializadas
     */
    public ReactivoDTO obtenerReactivo(int idReactivo) {
        ReactivoDTO reactivo = null;
        try{
            reactivo = Enlace.getPersistencia().obtenerReactivo(idReactivo);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return reactivo;
    }
    
    /**
     * Obtiene los reactivos pertenecientes al tema ingresado
     * 
     * @param tema el objeto TemaDTO del cuál se quiere obtener los reactivos del
     * mismo tema
     * 
     * @return una lista de ReactivoDTO con los reactivos del tema seleccionado,
     * en caso de que no exista ningún reactivo regresa null
     */
    public List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema) {
        List<ReactivoDTO> reactivos = null;
        try{
            reactivos = Enlace.getPersistencia().obtenerReactivosPorTema(tema);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return reactivos;
    }
  
    /**
     * Este método almacena el objeto ReactivoDTO, mediante la utilización de la
     * interface Persistencia, para llamar a las capas inferiores.
     * 
     * @param reactivo el objeto ReactivoDTO que se desea almacenar
     * @return un objeto Integer que contiene el número de id generado en este
     * registro o null, en caso de que haya habido problemas al guardar
     */
    public Integer guardarReactivo(ReactivoDTO reactivo) {
        Integer id = null;
        
        try{
            id = Enlace.getPersistencia().guardarEntidad(reactivo);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return id;
    }

    /**
     * Este método actualiza el objeto ReactivoDTO, mediante la utilización de
     * la interface Persistencia, para llamar a las capas inferiores.
     * 
     * @param reactivo el objeto ReactivoDTO que se desea actualizar
     * @return true si la actualización se realizó correctamente o false, en 
     * caso de que haya habido problemas al modificar
     */
    public boolean modificarReactivo(ReactivoDTO reactivo) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().modificarEntidad(reactivo);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
   
    /**
     * Elimina todos los reactivos de los datos de la aplicación en la lista de
     * entrada
     * 
     * @param reactivos la lista de reactivos a eliminar
     * @return true si la operación se realizó correctamente o false si ocurrió
     * un error
     */
    public boolean eliminarReactivos(List<ReactivoDTO> reactivos) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().eliminarReactivos(reactivos);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
}