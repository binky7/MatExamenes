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
import modelo.dto.ExamenDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 * Esta clase se encarga de enviar las peticiones del control vista del caso de
 * uso de Mantener Exámenes a la interface Persistencia, la cuál oculta la forma
 * en la cuál se tiene acceso a capas inferiores. El Delegate se encarga de
 * delegar el trabajo a esta interface y obtener los datos que se le pidan.
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class MantenerExamenesDELEGATE {
    
    /**
     * Obtiene los cursos existentes llamando a la interface Persistencia.
     * 
     * @return una lista de CursoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> cursos = null;
        
        try {
            //Llama a la Persistencia y requiere objetos de clase CursoDTO
            cursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return cursos;
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
     * Obtiene los temas pertenecientes al curso y bloque seleccionados
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener sus temas
     * @param bloque el entero que representa el bloque del que se quieren
     * obtener los temas
     * 
     * @return una lista de TemaDTO con los temas del curso y bloque
     * seleccionados, en caso de que no exista ningún tema regresa null
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso, int bloque) {
        List<TemaDTO> temas = null;
        try {
            //Por medio de la interface Persistencia se obtiene los temas del
            //curso y bloque
            temas = Enlace.getPersistencia().obtenerTemasDeCurso(curso, bloque);
        } catch(RemoteException | NotBoundException ex) {
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
     * Este metodo obtiene los reactivos pertenecientes a los temas ingresados
     * en la lista temas, donde de cada tema en la lista se selecciona la cantidad
     * correspondiente de reactivos aleatoriamente. El resultado es la lista
     * combinada de todos los reactivos seleccionados aleatoriamente de los
     * temas de la lista
     * 
     * @param temas la lista de TemaDTO que representa los temas de los que se
     * desea obtener aleatoriamente los reactivos
     * @param cantidades el número de de reactivos por cada tema que se desea
     * seleccionar aleatoriamente
     * @return una lista de ReactivoDTO seleccionada aleatoriamente de todos los
     * temas de la lista temas, o null en caso de que las cantidades ingresadas
     * sobrepasen la cantidad de reactivos reales en alguno de los temas.
     */
    public List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas,
            List<Integer> cantidades) {
        List<ReactivoDTO> reactivos = null;
        try{
            reactivos = Enlace.getPersistencia()
                    .obtenerReactivosAleatorios(temas, cantidades);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return reactivos;
    }
    
    /**
     * Este método es utilizado para obtener el objeto examen perteneciente
     * al id ingresado
     * 
     * @param idExamen el id del examen que se quiere obtener
     * @return El objeto ExamenDTO con sus relaciones completamente inicializadas
     * hasta reactivos. (Las incorrectas de los reactivos no están inicializadas,
     * tampoco los temas del curso)
     */
    public ExamenDTO obtenerExamen(int idExamen) {
        ExamenDTO examen = null;
        try{
            examen = Enlace.getPersistencia().obtenerExamen(idExamen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examen;
    }
    
    /**
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes disponibles en ese curso u obteniendo sólo
     * los exámenes que fueran públicos o aquellos que fueran hechos por el usuario
     * actual del sistema, y que por supuesto coincidieran con el curso ingresado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * sean del curso ingresado. En caso de que la bandera sea false el sistema
     * recupera los exámenes de permiso público y todos aquellos que sean hechos
     * por el usuario actual, además de que coincidieran con el curso ingresado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se decida
     * enviar false en el parámetro todos. Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresaría los exámenes que pertenezcan
     * al curso y que además fueran públicos o hechos por el usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes = null;
        try{
            examenes = Enlace.getPersistencia().obtenerExamenesPorCurso(curso,
                    todos, maestro);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes que coincidan con el nombre u obteniendo sólo
     * los exámenes que fueran públicos o aquellos que fueran hechos por el usuario
     * actual del sistema, y que por supuesto coincidieran con el nombre ingresado
     * 
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * coincidan con el nombre ingresado. En caso de que la bandera sea false el
     * sistema recupera los exámenes de permiso público y todos aquellos que sean
     * hechos por el usuario actual, además de que coincidieran con el nombre
     * ingresado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se decida
     * enviar false en el parámetro todos. Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresaría los exámenes que coincidieran
     * con el nombre ingresado y que además fueran públicos o hechos por el
     * usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorNombre(String nombre, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes = null;
        try{
            examenes = Enlace.getPersistencia().obtenerExamenesPorNombre(nombre,
                    todos, maestro);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado y el curso
     * seleccionado permitiendo seleccionar entre dos modalidades para recuperar
     * un examen, obteniendo todos los exámenes que coincidan con el nombre y el
     * curso u obteniendo sólo los exámenes que fueran públicos o aquellos que
     * fueran hechos por el usuario actual del sistema, y que por supuesto
     * coincidieran con el nombre ingresado y el curso seleccionado
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * coincidan con el nombre ingresado y el curso seleccionado. En caso de que
     * la bandera sea false el sistema recupera los exámenes de permiso público
     * y todos aquellos que sean hechos por el usuario actual, además de que
     * coincidieran con el nombre ingresado y el curso seleccionado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se
     * decida enviar false en el parámetro todos. Sirve para filtrar la búsqueda
     * por el autor del examen. Esta consulta regresaría los exámenes que
     * coincidieran con el nombre ingresado y el curso seleccionado y que además
     * fueran públicos o hechos por el usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCursoYNombre(CursoDTO curso,
            String nombre, boolean todos, UsuarioDTO maestro) {
        List<ExamenDTO> examenes = null;
        try{
            examenes = Enlace.getPersistencia()
                    .obtenerExamenesPorCursoYNombre(curso, nombre, todos,
                            maestro);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
    
    /**
     * Este método almacena el objeto ExamenDTO, mediante la utilización de la
     * interface Persistencia, para llamar a las capas inferiores.
     * 
     * @param examen el objeto ExamenDTO que se desea almacenar
     * @return un objeto Integer que contiene el número de id generado en este
     * registro o null, en caso de que haya habido problemas al guardar
     */
    public Integer guardarExamen(ExamenDTO examen) {
        Integer id = null;
        
        try{
            id = Enlace.getPersistencia().guardarEntidad(examen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return id;
    }
    
    /**
     * Este método actualiza el objeto ExamenDTO, mediante la utilización de
     * la interface Persistencia, para llamar a las capas inferiores.
     * 
     * @param examen el objeto ExamenDTO que se desea actualizar
     * @return true si la actualización se realizó correctamente o false, en 
     * caso de que haya habido problemas al modificar
     */
    public boolean modificarExamen(ExamenDTO examen) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().modificarEntidad(examen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
    
    /**
     * Elimina el examen de entrada de los datos de la aplicación
     * 
     * @param examen el objeto ExamenDTO a eliminar
     * @return true si la operación se realizó correctamente o false si ocurrió
     * un error
     */
    public boolean eliminarExamen(ExamenDTO examen) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().eliminarEntidad(examen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
}