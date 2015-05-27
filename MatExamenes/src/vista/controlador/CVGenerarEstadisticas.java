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
package vista.controlador;

import control.delegate.GenerarEstadisticasDELEGATE;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.TablaEstadisticas;

/**
 * Esta clase se encarga de enviar las peticiones de las vistas del caso de uso
 * de Generar Estadísticas al delegate del mismo caso de uso, en el cuál se tiene
 * acceso a capas inferiores para acceder a la base de datos. También mantiene
 * en memoria los objetos dto que se utilizan para el caso de uso.
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class CVGenerarEstadisticas {
    
    /**
     * Objeto delegate del caso de uso Generar Estadísticas. Delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación
     */
    private final GenerarEstadisticasDELEGATE generarEstadisticasDELEGATE;
    /**
     * Lista de objetos ExamenDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<ExamenDTO> examenes;
  
    /**
     * Crea un objeto CVGenerarEstadísticas e inicializa sus atributos
     */
    public CVGenerarEstadisticas() {
        generarEstadisticasDELEGATE = new GenerarEstadisticasDELEGATE();
    }
    
    /**
     * Obtiene los cursos existentes llamando al delegate del caso de uso.
     * 
     * @return una lista de CursoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null
     */
    public List<CursoDTO> obtenerCursos() {
        
        return generarEstadisticasDELEGATE.obtenerCursos();
    }

    /**
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * obteniendo todos los exámenes disponibles en ese curso
     * 
     * @param nombreCurso el nombre del curso del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCurso(String nombreCurso) {   
        CursoDTO curso = new CursoDTO();
        
        curso.setNombre(nombreCurso);
        
        List<ExamenDTO> listaExamenes = generarEstadisticasDELEGATE
                .obtenerExamenesPorCurso(curso);
        
        examenes = listaExamenes;
        
        return listaExamenes;
    }
    
    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los grupos por los exámenes ingresados en forma de
     * índices en la lista ingresada.
     * 
     * @param indexesExamen la lista de índices de examen que representa los
     * objetos ExamenDTO seleccionados en la vista.
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los grupos
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<Integer> indexesExamen) {
        TablaEstadisticas tabla = null;
        
        if(examenes != null && !examenes.isEmpty()) {
            List<ExamenDTO> listaExamenes = new ArrayList<>();
            
            //Obtener los objetos examen en base a sus índices
            for(int index : indexesExamen) {
                ExamenDTO examen = examenes.get(index);
                
                listaExamenes.add(examen);
            }
            
            tabla = generarEstadisticasDELEGATE
                    .generarEstadisticasPorGrupos(listaExamenes);
        }
        
        return tabla;
    }
   
    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los grados por los exámenes ingresados en forma de
     * índices en la lista ingresada.
     * 
     * @param indexesExamen la lista de índices de examen que representa los
     * objetos ExamenDTO seleccionados en la vista.
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los grados
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorGrados(
            List<Integer> indexesExamen) {
        
        TablaEstadisticas tabla = null;
        
        if(examenes != null && !examenes.isEmpty()) {
            List<ExamenDTO> listaExamenes = new ArrayList<>();
            
            //Obtener los objetos examen en base a sus índices
            for(int index : indexesExamen) {
                ExamenDTO examen = examenes.get(index);
                
                listaExamenes.add(examen);
            }
            
            tabla = generarEstadisticasDELEGATE
                    .generarEstadisticasPorGrados(listaExamenes);
        }
        
        return tabla;
    }
   
    /**
     * Este método sirve para generar un objeto TablaEstadisticas con los
     * promedios de todos los turnos por los exámenes ingresados en forma de
     * índices en la lista ingresada.
     * 
     * @param indexesExamen la lista de índices de examen que representa los
     * objetos ExamenDTO seleccionados en la vista.
     * 
     * @return un objeto TablaEstadisticas con los promedios de todos los turnos
     * por los exámenes elegidos o null en caso de que no existan grupos.
     * También se puede regresar una tabla con celdas vacías (null), lo que
     * indica que no existe ningún grupo que haya contestado los exámenes
     * seleccionados.
     */
    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<Integer> indexesExamen) {
        
        TablaEstadisticas tabla = null;
        
        if(examenes != null && !examenes.isEmpty()) {
            List<ExamenDTO> listaExamenes = new ArrayList<>();
            
            //Obtener los objetos examen en base a sus índices
            for(int index : indexesExamen) {
                ExamenDTO examen = examenes.get(index);
                
                listaExamenes.add(examen);
            }
            
            tabla = generarEstadisticasDELEGATE
                    .generarEstadisticasPorTurnos(listaExamenes);
        }
        
        return tabla;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto.
     */
    public void liberarMemoria() {
        examenes = null;
    }
    
}