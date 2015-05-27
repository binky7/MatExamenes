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
import modelo.dto.GrupoDTO;
import modelo.dto.TablaEstadisticas;
import remoteAccess.Enlace;

/**
 * Esta clase se encarga de enviar las peticiones del control vista del caso de
 * uso de Generar Estadísticas a la interface Persistencia, la cuál oculta la forma
 * en la cuál se tiene acceso a capas inferiores. El Delegate se encarga de
 * delegar el trabajo a esta interface y obtener los datos que se le pidan.
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class GenerarEstadisticasDELEGATE {
    
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
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * obteniendo todos los exámenes disponibles en ese curso
     * 
     * @param curso el objeto CursoDTO del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso) {
        List<ExamenDTO> examenes = null;
        
        try{
            //Esta llamada le indica a la persistencia que se desean obtener
            //los exámenes que pertenezcan al parámetro curso, con la bandera
            //true que indica que regrese todas las coincidencias, sin importar
            //que sean públicas o privadas, y el tercer parámetro no es necesario
            //por lo que se envía null.
            examenes = Enlace.getPersistencia().obtenerExamenesPorCurso(curso,
                    true, null);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
  
    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los grupos por los exámenes ingresados
     * 
     * @param examenes la lista de ExamenDTO de los que se desea obtener los
     * promedios por grupo
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los grupos
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<ExamenDTO> examenes) {
        
        TablaEstadisticas tabla = null;
        
        try{
            //Primero obtiene la lista de todos los grupos existentes por medio
            //de la Persistencia
            List<GrupoDTO> grupos = Enlace.getPersistencia()
                    .obtenerEntidades(GrupoDTO.class);
            
            //Después genera la TablaEstadísticas en base a los exámenes
            //ingresados y los grupos que se obtuvieron
            tabla = Enlace.getPersistencia()
                    .generarEstadisticasPorGrupos(examenes, grupos);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return tabla;
    }
    
    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los grados por los exámenes ingresados
     * 
     * @param examenes la lista de ExamenDTO de los que se desea obtener los
     * promedios por grado
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los grados
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorGrados(
            List<ExamenDTO> examenes) {
        
        TablaEstadisticas tabla = null;
        
        try {
            tabla = Enlace.getPersistencia()
                    .generarEstadisticasPorGrados(examenes);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return tabla;
    }
    
    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los turnos por los exámenes ingresados en forma de
     * índices en la lista ingresada.
     * 
     * @param examenes la lista de ExamenDTO de los que se desea obtener los
     * promedios por turno
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los turnos
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<ExamenDTO> examenes) {
        
        TablaEstadisticas tabla = null;
        
        try {
            tabla = Enlace.getPersistencia()
                    .generarEstadisticasPorTurnos(examenes);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return tabla;
    }
    
}